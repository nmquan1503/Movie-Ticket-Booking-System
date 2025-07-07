package com.nmquan1503.backend_springboot.services.theater;

import com.nmquan1503.backend_springboot.dtos.responses.theater.SeatDetailResponse;
import com.nmquan1503.backend_springboot.entities.theater.Seat;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.mappers.theater.SeatMapper;
import com.nmquan1503.backend_springboot.repositories.theater.SeatRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SeatService {

    SeatRepository seatRepository;

    SeatMapper seatMapper;

    StringRedisTemplate redisTemplate;

    public List<SeatDetailResponse> getSeatDetailsByShowtimeId(Long showtimeId) {
        List<Seat> seats = seatRepository.findByShowtimeId(showtimeId);
        List<Long> lockedSeatIds = seatRepository.findLockedSeatIdsByShowtimeId(showtimeId);
        Set<Long> setLockedSeatIds = new HashSet<>(lockedSeatIds);
        List<SeatDetailResponse> responses = seatMapper.toListSeatDetailResponse(seats);
        for (SeatDetailResponse response : responses) {
            response.setLocked(setLockedSeatIds.contains(response.getId()));
        }
        return responses;
    }

    public List<Seat> fetchByIds(List<Long> ids) {
        List<Seat> seats = seatRepository.findAllById(ids);
        if (seats.size() < ids.size()) {
            throw new GeneralException(ResponseCode.SEAT_NOT_FOUND);
        }
        return seats;
    }

    public boolean lockSeat(Long showtimeId, Long seatId, Long userId) {
        String key = generateSeatLockedKey(showtimeId, seatId);
        Boolean success = redisTemplate.opsForValue()
                .setIfAbsent(key, String.valueOf(userId), 5, TimeUnit.MINUTES);
        return Boolean.TRUE.equals(success);
    }

    public boolean lockListSeats(Long showtimeId, List<Long> seatIds, Long userId) {
        List<String> keys = generateListSeatLockedKeys(showtimeId, seatIds);
        String luaScript = """
                local keys = KEYS
                local user_id = ARGV[1]
                local expiration = tonumber(ARGV[2])
                for i = 1, #keys do
                    if redis.call('setnx', keys[i], user_id) == 0 then
                        return 0
                    end
                    redis.call('expire', keys[i], expiration)
                end
                return 1
                """;
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setScriptText(luaScript);
        script.setResultType(Long.class);
        Long result = redisTemplate.execute(
                script,
                keys,
                String.valueOf(userId),
                String.valueOf(TimeUnit.MINUTES.toSeconds(5))
        );
        return result == 1;
    }

    public void unlockSeat(Long showtimeId, Long seatId) {
        redisTemplate.delete(generateSeatLockedKey(showtimeId, seatId));
    }

    public boolean unlockListSeats(Long showtimeId, List<Long> seatIds, Long userId) {
        List<String> keys = generateListSeatLockedKeys(showtimeId, seatIds);
        String luaScript = """
                local keys = KEYS
                local user_id = ARGV[1]
                for i = 1, #keys do
                    if redis.call('get', keys[i]) ~= user_id then
                        return 0
                    end
                    redis.call('del', keys[i])
                end
                return 1
                """;
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setScriptText(luaScript);
        script.setResultType(Long.class);
        Long result = redisTemplate.execute(
                script,
                keys,
                String.valueOf(userId)
        );
        return result == 1;
    }

    private String generateSeatLockedKey(Long showtimeId, Long seatId) {
        return "seat_lock:" + showtimeId + ":" + seatId;
    }

    private List<String> generateListSeatLockedKeys(Long showtimeId, List<Long> seatIds) {
        return seatIds.stream().map(
                seatId -> generateSeatLockedKey(showtimeId, seatId)
        ).toList();
    }

    public Set<Long> getLockedSeatsByShowtimeId(Long showtimeId) {
//        String pattern = "seat_lock:" + showtimeId + ":*";
//        Set<String> keys = redisTemplate.keys(pattern);
//        return keys.stream().map(
//                key -> {
//                    try {
//                        String[] parts = key.split(":");
//                        return Long.parseLong(parts[2]);
//                    }
//                    catch (Exception e) {
//                        return null;
//                    }
//                })
//                .filter(Objects::nonNull)
//                .collect(Collectors.toSet());
        return new HashSet<>(seatRepository.findLockedSeatIdsByShowtimeId(showtimeId));
    }

    public boolean areAvailable(List<Long> seatIds, Long showtimeId) {
        return seatRepository.areAvailable(seatIds, showtimeId);
    }

    public List<Seat> fetchSeatsByTicketId(Long ticketId) {
        return seatRepository.findByTicketId(ticketId);
    }

    public List<Seat> fetchSeatsByReservationId(Long reservationId) {
        return seatRepository.findByReservationId(reservationId);
    }


}
