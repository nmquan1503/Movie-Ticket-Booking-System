package com.nmquan1503.backend_springboot.services.movie;

import com.nmquan1503.backend_springboot.dtos.responses.movie.CategoryResponse;
import com.nmquan1503.backend_springboot.entities.movie.Category;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.mappers.movie.CategoryMapper;
import com.nmquan1503.backend_springboot.repositories.movie.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {

    CategoryRepository categoryRepository;

    CategoryMapper categoryMapper;

    public List<CategoryResponse> getCategoriesByMovieId(Long movieId) {
        return categoryRepository.findByMovieId(movieId)
                .stream().map(categoryMapper::toCategoryResponse).toList();
    }

    public List<CategoryResponse> getCategoriesByIds(List<Byte> categoryIds) {
        List<Category> categories = findCategoriesByIds(categoryIds);
        return categories.stream().map(
                categoryMapper::toCategoryResponse
        ).toList();
    }

    List<Category> findCategoriesByIds(List<Byte> ids) {
        if (ids == null) {
            return new ArrayList<>();
        }
        List<Category> categories = categoryRepository.findAllById(ids);
        if (categories.size() < ids.size()) {
            throw new GeneralException(ResponseCode.CATEGORY_NOT_FOUND);
        }
        return categories;
    }

}
