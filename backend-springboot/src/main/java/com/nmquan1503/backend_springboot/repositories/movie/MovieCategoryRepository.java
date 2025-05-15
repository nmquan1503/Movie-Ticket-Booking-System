package com.nmquan1503.backend_springboot.repositories.movie;

import com.nmquan1503.backend_springboot.entities.movie.Category;
import com.nmquan1503.backend_springboot.entities.movie.Movie;
import com.nmquan1503.backend_springboot.entities.movie.MovieCategory;
import com.nmquan1503.backend_springboot.repositories.movie.custom.CustomMovieCategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCategoryRepository extends JpaRepository<MovieCategory, Long>, CustomMovieCategoryRepository {

    boolean existsByMovieAndCategory(Movie movie, Category category);

}
