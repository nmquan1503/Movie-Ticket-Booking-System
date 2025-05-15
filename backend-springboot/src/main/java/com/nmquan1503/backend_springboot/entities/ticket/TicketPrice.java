package com.nmquan1503.backend_springboot.entities.ticket;

import com.nmquan1503.backend_springboot.entities.theater.RoomType;
import com.nmquan1503.backend_springboot.entities.theater.SeatType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

@Entity
@Table(name = "ticket_price")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "seat_type_id", nullable = false)
    SeatType seatType;

    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    RoomType roomType;

    @Column(name = "time_range_start")
    LocalTime timeRangeStart;

    @Column(name = "time_range_end")
    LocalTime timeRangeEnd;

    @Column(nullable = false)
    Double price;

}
