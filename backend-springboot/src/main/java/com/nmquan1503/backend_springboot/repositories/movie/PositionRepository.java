package com.nmquan1503.backend_springboot.repositories.movie;

import com.nmquan1503.backend_springboot.entities.movie.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

    long countByIdIn(List<Integer> ids);

}
