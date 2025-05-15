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

    @Column(name = "banner_url")
    String bannerURL;

    @Column(name = "trailer_url")
    String trailerURL;

    String overview;

    @Column(name = "released_date")
    LocalDate releasedDate;

    @Column(nullable = false)
    Short duration;

    @ManyToOne
    @JoinColumn(name = "original_language_id", nullable = false)
    Language originalLanguage;

    @ManyToOne
    @JoinColumn(name = "subtitle_language_id")
    Language subtitleLanguage;

    @ManyToOne
    @JoinColumn(name = "age_rating_id")
    AgeRating ageRating;

    @Column(name = "average_rating")
    @Builder.Default
    Double averageRating = 0.0;

    @Column(name = "rating_count")
    @Builder.Default
    Integer ratingCount = 0;

}
