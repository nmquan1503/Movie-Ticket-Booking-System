package com.nmquan1503.backend_springboot.repositories.theater;

import com.nmquan1503.backend_springboot.entities.theater.RoomTypeFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomTypeFeatureRepository extends JpaRepository<RoomTypeFeature, Integer> {

    List<RoomTypeFeature> findByRoomTypeId(Byte roomTypeId);

}
