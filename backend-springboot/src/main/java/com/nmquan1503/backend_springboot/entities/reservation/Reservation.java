package com.nmquan1503.backend_springboot.entities.reservation;

import com.nmquan1503.backend_springboot.entities.showtime.Showtime;
import com.nmquan1503.backend_springboot.entities.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "showtime_id", nullable = false)
    Showtime showtime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Column(name = "start_time", nullable = false)
    @Builder.Default
    LocalDateTime startTime = LocalDateTime.now();

    @Column(name = "end_time", nullable = false)
    LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    ReservationStatus status;

}
