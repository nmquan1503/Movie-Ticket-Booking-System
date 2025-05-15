package com.nmquan1503.backend_springboot.repositories.movie.custom;

import com.nmquan1503.backend_springboot.entities.movie.Person;

import java.util.List;

public interface CustomPersonRepository {

    List<Person> findActorsByMovieId(Long movieId);

    List<Person> findDirectorsByMovieId(Long movieId);

}
