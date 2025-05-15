package com.nmquan1503.backend_springboot.repositories.movie.custom;

import java.util.List;

public interface CustomMovieDirectorRepository {

    void deleteAllByMovieIdAndDirectorIdNotIn(Long movieId, List<Long> directorIds);

}
