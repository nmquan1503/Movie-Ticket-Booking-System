package com.nmquan1503.backend_springboot.entities.movie;

import com.nmquan1503.backend_springboot.entities.showtime.Showtime;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "movies")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String title;

    @Column(name = "poster_url")
    String posterURL;

    @Column(name = "backdrop_url")
    String backdropURL;

    @Column(name = "trailer_url")
    String trailerURL;

    String tagline;

    String overview;

    @Column(name = "released_date")
    LocalDate releasedDate;

    @Column(nullable = false)
    Short duration;

    @ManyToOne
    @JoinColumn(name = "original_language_id", nullable = false)
    Language originalLanguage;

    @ManyToOne
    @JoinColumn(name = "age_rating_id")
    AgeRating ageRating;
}
