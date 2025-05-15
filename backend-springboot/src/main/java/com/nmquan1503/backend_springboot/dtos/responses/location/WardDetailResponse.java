package com.nmquan1503.backend_springboot.dtos.responses.location;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WardDetailResponse {

    Integer id;
    String name;
    DistrictODetailResponse district;

}
