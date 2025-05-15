package com.nmquan1503.backend_springboot.repositories.movie.custom;

import java.util.List;

public interface CustomMovieActorRepository {

    void deleteAllByMovieIdAndActorIdNotIn(Long movieId, List<Long> actorIds);

}
