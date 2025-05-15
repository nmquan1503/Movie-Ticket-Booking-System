package com.nmquan1503.backend_springboot.entities.theater;

import com.nmquan1503.backend_springboot.entities.reservation.ReservationSeat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "seats")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    Room room;

    @Column(name = "row_label", nullable = false)
    Character rowLabel;

    @Column(name = "column_label", nullable = false)
    Byte columnLabel;

    @Column(name = "x_position", nullable = false)
    Byte positionX;

    @Column(name = "y_position", nullable = false)
    Byte positionY;

    @ManyToOne
    @JoinColumn(name = "seat_type_id", nullable = false)
    SeatType type;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    SeatStatus status;

}
