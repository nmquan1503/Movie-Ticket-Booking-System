package com.nmquan1503.backend_springboot.mappers.location;

import com.nmquan1503.backend_springboot.dtos.responses.location.ProvinceDetailResponse;
import org.mapstruct.Mapper;

import com.nmquan1503.backend_springboot.entities.location.Province;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProvinceMapper {

    ProvinceDetailResponse toProvinceDetailResponse(Province province);

}
