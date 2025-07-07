package com.nmquan1503.backend_springboot.mappers.movie;

import com.nmquan1503.backend_springboot.dtos.responses.movie.PositionResponse;
import com.nmquan1503.backend_springboot.entities.movie.Position;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {
                DepartmentMapper.class
        }
)
public interface PositionMapper {

    PositionResponse toPositionResponse(Position position);

}
