package com.nmquan1503.backend_springboot.repositories.showtime.custom;

import java.time.LocalDateTime;

public interface CustomShowtimeRepository {

    boolean isScheduleConflict(Integer roomId, LocalDateTime startTime, Short duration);

}
