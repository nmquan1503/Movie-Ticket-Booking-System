package com.nmquan1503.backend_springboot.repositories.movie.custom;

import com.nmquan1503.backend_springboot.dtos.internal.MovieReviewStats;

import java.util.List;
import java.util.Map;

public interface CustomMovieReviewRepository {

    MovieReviewStats findMovieReviewStatsByMovieId(Long movieId);

    Map<Long, MovieReviewStats> findListMovieReviewsStatsByMovieIds(List<Long> movieIds);

}
