package com.nmquan1503.backend_springboot.repositories.showtime;

import com.nmquan1503.backend_springboot.entities.showtime.ShowtimeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowtimeStatusRepository extends JpaRepository<ShowtimeStatus, Byte> {

    Optional<ShowtimeStatus> findByName(String name);

}
