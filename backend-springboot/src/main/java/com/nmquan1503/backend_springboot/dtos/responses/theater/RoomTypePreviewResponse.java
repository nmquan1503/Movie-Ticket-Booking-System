package com.nmquan1503.backend_springboot.dtos.responses.theater;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomTypePreviewResponse {

    Byte id;
    String name;
    String thumbnailURL;
    String slogan;

}
