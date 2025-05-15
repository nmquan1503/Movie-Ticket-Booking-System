package com.nmquan1503.backend_springboot.mappers.user;

import org.mapstruct.Mapper;

import com.nmquan1503.backend_springboot.dtos.responses.user.GenderOptionResponse;
import com.nmquan1503.backend_springboot.entities.user.Gender;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface GenderMapper {
    
    GenderOptionResponse toGenderOptionResponse(Gender gender);

}
