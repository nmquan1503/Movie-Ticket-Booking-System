package com.nmquan1503.backend_springboot.mappers.movie;

import com.nmquan1503.backend_springboot.dtos.responses.movie.CategoryResponse;
import com.nmquan1503.backend_springboot.entities.movie.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CategoryMapper {

    CategoryResponse toCategoryResponse(Category category);

    List<CategoryResponse> toListCategoryResponse (List<Category> categories);

}
