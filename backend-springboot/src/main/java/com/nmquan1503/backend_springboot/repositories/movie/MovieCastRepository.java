package com.nmquan1503.backend_springboot.repositories.movie;

import com.nmquan1503.backend_springboot.entities.movie.MovieCast;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieCastRepository extends JpaRepository<MovieCast, Long> {

    List<MovieCast> findByMovieIdOrderByOrderAsc(Long movieId);

    long deleteByMovieIdAndIdIn(Long movieId, List<Long> ids);

    long countByMovieIdAndIdIn(Long movieId, List<Long> ids);

    Page<MovieCast> findByMovieIdOrderByOrderAsc(Long movieId, Pageable pageable);

}
