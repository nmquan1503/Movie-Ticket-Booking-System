package com.nmquan1503.backend_springboot.dtos.internal;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieViewHistoryLog {

    Long userId;
    Long movieId;
    LocalDateTime startTime;

}
