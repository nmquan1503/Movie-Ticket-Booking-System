package com.nmquan1503.backend_springboot.repositories.movie.custom;

import com.nmquan1503.backend_springboot.dtos.responses.movie.MovieListItemResponse;
import com.nmquan1503.backend_springboot.dtos.responses.movie.MoviePreviewResponse;
import com.nmquan1503.backend_springboot.entities.movie.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomMovieRepository {

    List<MoviePreviewResponse> getTrendingMovieSummaries(int number);

    List<MoviePreviewResponse> getNowShowingMovieSummaries();

    List<MoviePreviewResponse> getComingSoonMovieSummaries();

    Page<MovieListItemResponse> findMovieCatalogs(Pageable pageable);

    List<Movie> findTrendingMovies(int limit);

}
