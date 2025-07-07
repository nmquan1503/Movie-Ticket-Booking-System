package com.nmquan1503.backend_springboot.dtos.responses.theater;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomDetailResponse {

    Integer id;
    String name;
    Byte rowCount;
    Byte columnCount;
    BranchOptionResponse branch;
    RoomTypeSimpleResponse type;
    RoomStatusResponse status;

}
