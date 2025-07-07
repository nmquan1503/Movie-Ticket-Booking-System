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

    @Column(name = "day_of_week", nullable = false)
    Byte dayOfWeek;

    @Column(name = "time_range_start", nullable = false)
    LocalTime timeRangeStart;

    @Column(name = "time_range_end", nullable = false)
    LocalTime timeRangeEnd;

    @Column(nullable = false)
    Double price;

}
