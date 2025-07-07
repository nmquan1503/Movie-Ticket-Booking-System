package com.nmquan1503.backend_springboot.repositories.movie;

import com.nmquan1503.backend_springboot.entities.movie.MovieCrew;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieCrewRepository extends JpaRepository<MovieCrew, Long> {

    List<MovieCrew> findByMovieId(Long movieId);

    List<MovieCrew> findByMovieIdAndIdIn(Long movieId, List<Long> ids);

    long countByMovieIdAndIdIn(Long movieId, List<Long> ids);

    long deleteByMovieIdAndIdIn(Long movieId, List<Long> ids);

    Page<MovieCrew> findByMovieId(Long movieId, Pageable pageable);
}
