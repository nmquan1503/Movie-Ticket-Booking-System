package com.nmquan1503.backend_springboot.mappers.location;

import com.nmquan1503.backend_springboot.dtos.responses.location.WardDetailResponse;
import org.mapstruct.Mapper;

import com.nmquan1503.backend_springboot.entities.location.Ward;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface WardMapper {
    
    WardDetailResponse toWardDetailResponse(Ward ward);

}
