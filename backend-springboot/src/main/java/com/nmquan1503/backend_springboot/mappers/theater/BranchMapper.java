package com.nmquan1503.backend_springboot.mappers.theater;

import com.nmquan1503.backend_springboot.dtos.responses.theater.BranchOptionResponse;
import com.nmquan1503.backend_springboot.entities.theater.Branch;
import com.nmquan1503.backend_springboot.mappers.location.ProvinceMapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ProvinceMapper.class}
)
public interface BranchMapper {

    @Mapping(source = "ward.district.province", target = "province")
    BranchOptionResponse toBranchOptionResponse(Branch branch);

    List<BranchOptionResponse> toListBranchOptionResponse(List<Branch> branches);

}
