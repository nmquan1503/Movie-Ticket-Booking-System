package com.nmquan1503.backend_springboot.entities.theater;

import com.nmquan1503.backend_springboot.entities.showtime.Showtime;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "rooms")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    Branch branch;

    @Column(nullable = false)
    String name;

    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    RoomType type;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    RoomStatus status;

}
