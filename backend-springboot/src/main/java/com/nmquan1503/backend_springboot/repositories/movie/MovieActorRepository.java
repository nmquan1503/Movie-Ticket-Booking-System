package com.nmquan1503.backend_springboot.repositories.movie;

import com.nmquan1503.backend_springboot.entities.movie.Movie;
import com.nmquan1503.backend_springboot.entities.movie.MovieActor;
import com.nmquan1503.backend_springboot.entities.movie.Person;
import com.nmquan1503.backend_springboot.repositories.movie.custom.CustomMovieActorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MovieActorRepository extends JpaRepository<MovieActor, Long>, CustomMovieActorRepository {

    boolean existsByMovieAndActor(Movie movie, Person actor);

}
