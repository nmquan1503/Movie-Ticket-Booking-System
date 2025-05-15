package com.nmquan1503.backend_springboot.repositories.movie.custom;

import com.nmquan1503.backend_springboot.entities.movie.Category;

import java.util.List;

public interface CustomMovieCategoryRepository {

    void deleteAllByMovieIdAndCategoryIdNotIn(Long movieId, List<Byte> categoryIds);

}
