package com.nmquan1503.backend_springboot.repositories.theater;

import com.nmquan1503.backend_springboot.entities.theater.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
}
