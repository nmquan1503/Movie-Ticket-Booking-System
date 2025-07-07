package com.nmquan1503.backend_springboot.mappers.movie;

import com.nmquan1503.backend_springboot.dtos.responses.movie.DepartmentResponse;
import com.nmquan1503.backend_springboot.entities.movie.Department;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DepartmentMapper {

    DepartmentResponse toDepartmentResponse(Department department);

}
