package com.nmquan1503.backend_springboot.repositories.movie;

import com.nmquan1503.backend_springboot.entities.movie.MovieReview;
import com.nmquan1503.backend_springboot.repositories.movie.custom.CustomMovieReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieReviewRepository extends JpaRepository<MovieReview, Long>, CustomMovieReviewRepository {

    Page<MovieReview> findByMovieId(Long movieId, Pageable pageable);

    List<MovieReview> findByMovieId(Long movieId);

    List<MovieReview> findByMovieIdAndCreationTimeGreaterThanEqualAndCreationTimeLessThan(Long movieId, LocalDateTime from, LocalDateTime to);

}
