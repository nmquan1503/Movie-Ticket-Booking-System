package com.nmquan1503.backend_springboot.dtos.responses.theater;

import com.nmquan1503.backend_springboot.dtos.responses.location.ProvinceDetailResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BranchOptionResponse {

    Short id;
    String name;
    ProvinceDetailResponse province;
    BranchStatusResponse status;

}
