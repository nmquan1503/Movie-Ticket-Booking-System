package com.nmquan1503.backend_springboot.mappers.location;

import com.nmquan1503.backend_springboot.dtos.responses.location.DistrictODetailResponse;
import org.mapstruct.Mapper;

import com.nmquan1503.backend_springboot.entities.location.District;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {
                ProvinceMapper.class
        }
)
public interface DistrictMapper {
    
    DistrictODetailResponse toDistrictDetailResponse(District district);

}
