package com.nmquan1503.backend_springboot.dtos.messages;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieViewHistoryMessage {

    Long movieId;
    LocalDateTime startTime;

}
