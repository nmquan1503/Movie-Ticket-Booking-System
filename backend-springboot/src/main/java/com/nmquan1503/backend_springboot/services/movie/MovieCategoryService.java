package com.nmquan1503.backend_springboot.services.movie;

import com.nmquan1503.backend_springboot.entities.movie.Category;
import com.nmquan1503.backend_springboot.entities.movie.Movie;
import com.nmquan1503.backend_springboot.entities.movie.MovieCategory;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.repositories.movie.MovieCategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieCategoryService {

    MovieCategoryRepository movieCategoryRepository;

    CategoryService categoryService;

    public void save(Long movieId, List<Byte> categoryIds) {
        if (movieId == null || categoryIds == null || categoryIds.isEmpty()) {
            return;
        }
        if (!categoryService.allUniqueCategoryIdsExist(categoryIds)) {
            throw new GeneralException(ResponseCode.CATEGORY_NOT_FOUND);
        }
        Movie fakeMovie = Movie.builder().id(movieId).build();
        List<MovieCategory> movieCategoryList = categoryIds.stream().map(
                categoryId -> MovieCategory.builder()
                        .movie(fakeMovie)
                        .category(Category.builder().id(categoryId).build())
                        .build()
        ).toList();
        movieCategoryRepository.saveAll(movieCategoryList);
    }

    public void delete(Long movieId, List<Byte> categoryIds) {
        if (movieId == null || categoryIds == null || categoryIds.isEmpty()) {
            return;
        }
        if (movieCategoryRepository.deleteByMovieIdAndCategoryIdIn(movieId, categoryIds) != categoryIds.size()) {
            throw new GeneralException(ResponseCode.CATEGORY_NOT_FOUND);
        }
    }

    public Map<Long, List<Category>> fetchCategoryByMovieIds(List<Long> movieIds) {
        List<MovieCategory> items = movieCategoryRepository.findByMovieIdIn(movieIds);
        Map<Long, List<Category>> map = new HashMap<>();
        for (MovieCategory item : items) {
            if (map.containsKey(item.getMovie().getId())) {
                map.get(item.getMovie().getId()).add(item.getCategory());
            }
            else {
                List<Category> categories = new ArrayList<>();
                categories.addLast(item.getCategory());
                map.put(item.getMovie().getId(), categories);
            }
        }
        return map;
    }

}
