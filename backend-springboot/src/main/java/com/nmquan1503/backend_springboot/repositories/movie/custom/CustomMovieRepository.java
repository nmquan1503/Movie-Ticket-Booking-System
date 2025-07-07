package com.nmquan1503.backend_springboot.repositories.movie.custom;

import com.nmquan1503.backend_springboot.dtos.responses.movie.MovieListItemResponse;
import com.nmquan1503.backend_springboot.dtos.responses.movie.MoviePreviewResponse;
import com.nmquan1503.backend_springboot.entities.movie.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomMovieRepository {

    List<Movie> findTrendingMovies(int limit);

    Page<Movie> findAllSortByFinalScore(Pageable pageable);

    Page<Movie> findNowShowingSortByFinalScore(Pageable pageable);

    Page<Movie> findUpComingSortByFinalScore(Pageable pageable);

    List<Movie> findAllSortByFinalScore();

    List<Long> findIdsByListIds(List<Long> ids);

}
