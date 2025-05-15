package com.nmquan1503.backend_springboot.services.showtime;

import com.nmquan1503.backend_springboot.entities.showtime.ShowtimeStatus;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.repositories.showtime.ShowtimeStatusRepository;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShowtimeStatusService {

    ShowtimeStatusRepository showtimeStatusRepository;

    public boolean existsByShowtimeStatusId(Byte showtimeStatusId) {
        return showtimeStatusRepository.existsById(showtimeStatusId);
    }

    public ShowtimeStatus fetchByName(String name) {
        return showtimeStatusRepository.findByName(name)
                .orElseThrow(() -> new GeneralException(ResponseCode.SHOWTIME_STATUS_NOT_FOUND));
    }

    public ShowtimeStatus fetchById(Byte showtimeStatusId) {
        return showtimeStatusRepository.findById(showtimeStatusId)
                .orElseThrow(() -> new GeneralException(ResponseCode.SHOWTIME_STATUS_NOT_FOUND));
    }

}
