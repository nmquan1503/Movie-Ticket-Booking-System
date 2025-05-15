package com.nmquan1503.backend_springboot.services.movie;

import com.nmquan1503.backend_springboot.entities.movie.Category;
import com.nmquan1503.backend_springboot.entities.movie.Movie;
import com.nmquan1503.backend_springboot.entities.movie.MovieCategory;
import com.nmquan1503.backend_springboot.repositories.movie.MovieCategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieCategoryService {

    MovieCategoryRepository movieCategoryRepository;

    public void save(Long movieId, List<Byte> categoryIds) {
        if (movieId == null || categoryIds == null) {
            return;
        }
        movieCategoryRepository.deleteAllByMovieIdAndCategoryIdNotIn(movieId, categoryIds);
        List<MovieCategory> movieCategories = categoryIds.stream().map(
                categoryId -> MovieCategory.builder()
                        .movie(Movie.builder().id(movieId).build())
                        .category(Category.builder().id(categoryId).build())
                        .build()
        ).toList();
        for (MovieCategory movieCategory : movieCategories){
            if (!movieCategoryRepository.existsByMovieAndCategory(movieCategory.getMovie(), movieCategory.getCategory())) {
                movieCategoryRepository.save(movieCategory);
            }
        }
    }

}
