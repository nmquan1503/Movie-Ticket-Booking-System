package com.nmquan1503.backend_springboot.repositories.movie;

import com.nmquan1503.backend_springboot.entities.movie.Movie;
import com.nmquan1503.backend_springboot.entities.movie.MovieScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieScoreRepository extends JpaRepository<MovieScore, Movie> {
}
