package com.nmquan1503.backend_springboot.dtos.responses.theater;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomTypeDetailResponse {

    Byte id;
    String name;
    String iconURL;
    String overview;
    List<BranchSimpleResponse> branches;
    List<RoomTypeFeatureResponse> features;

}
