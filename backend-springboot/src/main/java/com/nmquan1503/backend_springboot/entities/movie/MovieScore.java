package com.nmquan1503.backend_springboot.entities.movie;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "movie_scores")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieScore {

    @Id
    @Column(name = "movie_id")
    Long movieId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "movie_id")
    Movie movie;

    @Column(name = "rating_score", nullable = false)
    Double ratingScore;

    @Column(name = "view_score", nullable = false)
    Double viewScore;

    @Column(name = "ticket_score", nullable = false)
    Double ticketScore;

    @Column(name = "showtime_score", nullable = false)
    Double showtimeScore;

    @Column(name = "final_score", nullable = false)
    Double finalScore;

    @Column(name = "last_updated")
    LocalDateTime lastUpdated;

}
