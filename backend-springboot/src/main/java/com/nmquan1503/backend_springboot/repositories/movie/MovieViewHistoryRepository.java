package com.nmquan1503.backend_springboot.repositories.movie;

import com.nmquan1503.backend_springboot.entities.movie.MovieViewHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovieViewHistoryRepository extends JpaRepository<MovieViewHistory, Long> {

    List<MovieViewHistory> findByMovieId(Long movieId);

    List<MovieViewHistory> findByMovieIdAndStartTimeGreaterThanEqualAndStartTimeLessThan(Long movieId, LocalDateTime from, LocalDateTime to);

}
