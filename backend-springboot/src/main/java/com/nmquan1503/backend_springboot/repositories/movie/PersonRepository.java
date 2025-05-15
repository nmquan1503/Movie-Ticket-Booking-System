package com.nmquan1503.backend_springboot.repositories.movie;

import com.nmquan1503.backend_springboot.entities.movie.Person;
import com.nmquan1503.backend_springboot.repositories.movie.custom.CustomPersonRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>, CustomPersonRepository {

}
