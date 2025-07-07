package com.nmquan1503.backend_springboot.services.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nmquan1503.backend_springboot.dtos.internal.MovieViewHistoryLog;
import com.nmquan1503.backend_springboot.dtos.messages.MovieViewHistoryMessage;
import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieViewHistoryCreationRequest;
import com.nmquan1503.backend_springboot.entities.movie.Movie;
import com.nmquan1503.backend_springboot.entities.movie.MovieViewHistory;
import com.nmquan1503.backend_springboot.entities.user.User;
import com.nmquan1503.backend_springboot.repositories.movie.MovieViewHistoryRepository;
import com.nmquan1503.backend_springboot.services.authentication.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieViewHistoryService {

    MovieViewHistoryRepository movieViewHistoryRepository;

    MovieService movieService;

    RedisTemplate<String, Object> redisTemplate;
    KafkaTemplate<String, MovieViewHistoryMessage> kafkaTemplate;
    AuthenticationService authenticationService;

    static String REDIS_CACHE_VIEW_KEY = "movie_view";
    static String REDIS_UPDATE_VIEW_SCORE_KEY = "update_view_score";
    static Duration USER_MOVIE_TTL = Duration.ofSeconds(20);
    static Duration USER_TOTAL_TTL = Duration.ofSeconds(60);
    static int USER_TOTAL_LIMIT = 10;

    List<MovieViewHistory> fetchByMovieId(Long movieId) {
        return movieViewHistoryRepository.findByMovieId(movieId);
    }

    List<MovieViewHistory> fetchMovieViewHistoryWithinPeriod(Long movieId, LocalDateTime from, LocalDateTime to) {
        return movieViewHistoryRepository.findByMovieIdAndStartTimeGreaterThanEqualAndStartTimeLessThan(movieId, from, to);
    }

    public void createView(MovieViewHistoryCreationRequest request) {
        Long userId = authenticationService.getCurrentUserId();
        if (isSpam(userId, request.getMovieId())) {
            return;
        }
        markView(userId, request.getMovieId());
        LocalDateTime startTime = LocalDateTime.now();
        MovieViewHistoryLog log = MovieViewHistoryLog.builder()
                .userId(userId)
                .movieId(request.getMovieId())
                .startTime(startTime)
                .build();
        saveViewInRedis(log);
        MovieViewHistoryMessage message = MovieViewHistoryMessage.builder()
                .movieId(request.getMovieId())
                .startTime(startTime)
                .build();
        redisTemplate.opsForList().rightPush(REDIS_UPDATE_VIEW_SCORE_KEY, message);
    }

    private void saveViewInRedis(MovieViewHistoryLog log) {
        redisTemplate.opsForList().rightPush(REDIS_CACHE_VIEW_KEY, log);
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void flushRedisViewHistoryToDB() {
        String luaScript = """
                    local result = redis.call('LRANGE', KEYS[1], 0, -1)
                    redis.call('DEL', KEYS[1])
                    return result
                """;
        DefaultRedisScript<List<Object>> script = new DefaultRedisScript<>();
        script.setScriptText(luaScript);
        script.setResultType((Class<List<Object>>) (Class<?>) List.class);
        List<Object> result = redisTemplate.execute(
                script,
                Collections.singletonList(REDIS_CACHE_VIEW_KEY)
        );
        if (result.isEmpty()) {
            return;
        }
        List<MovieViewHistory> views = new ArrayList<>();
        List<Long> movieIds = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        for (Object item : result) {
            try {
                MovieViewHistoryLog log = objectMapper.convertValue(item, MovieViewHistoryLog.class);
                views.add(MovieViewHistory.builder()
                                .user(User.builder().id(log.getUserId()).build())
                                .movie(Movie.builder().id(log.getMovieId()).build())
                                .startTime(log.getStartTime())
                        .build());
                movieIds.add(log.getMovieId());
            }
            catch (Exception e) {
                System.out.println("fail parse.");
            }
        }
        Set<Long> existMovieIds = new HashSet<>(movieService.fetchMovieIdsByListIds(movieIds));
        for (int i = 0; i < views.size(); i++) {
            if (!existMovieIds.contains(views.get(i).getMovie().getId())) {
                views.remove(i);
                i--;
            }
        }
        if (!views.isEmpty()) {
            movieViewHistoryRepository.saveAll(views);
        }
    }

    private boolean isSpam(Long userId, Long movieId) {
        String userMovieKey = String.format("viewed:%d:%d", userId, movieId);
        String userTotalKey = String.format("viewed:%d:count", userId);
        Boolean hasViewedMovie = redisTemplate.hasKey(userMovieKey);
        if (Boolean.TRUE.equals(hasViewedMovie)) {
            return true;
        }
        Long count = redisTemplate.opsForValue().increment(userMovieKey, 1);
        if (count != null && count == 1) {
            redisTemplate.expire(userTotalKey, USER_TOTAL_TTL);
        }
        return count != null && count > USER_TOTAL_LIMIT;
    }

    private void markView(Long userId, Long movieId) {
        String userMovieKey = String.format("viewed:%d:%d", userId, movieId);
        redisTemplate.opsForValue().set(userMovieKey, "1");
        redisTemplate.expire(userMovieKey, USER_MOVIE_TTL);
    }

}
