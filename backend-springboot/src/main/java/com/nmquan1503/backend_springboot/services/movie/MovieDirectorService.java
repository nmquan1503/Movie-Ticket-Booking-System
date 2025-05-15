package com.nmquan1503.backend_springboot.services.movie;

import com.nmquan1503.backend_springboot.entities.movie.Movie;
import com.nmquan1503.backend_springboot.entities.movie.MovieActor;
import com.nmquan1503.backend_springboot.entities.movie.MovieDirector;
import com.nmquan1503.backend_springboot.entities.movie.Person;
import com.nmquan1503.backend_springboot.repositories.movie.MovieDirectorRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieDirectorService {

    MovieDirectorRepository movieDirectorRepository;

    public void save(Long movieId, List<Long> directorIds) {
        if (movieId == null || directorIds == null) {
            return;
        }
        movieDirectorRepository.deleteAllByMovieIdAndDirectorIdNotIn(movieId, directorIds);
        List<MovieDirector> movieDirectors = directorIds.stream().map(
                directorId -> MovieDirector.builder()
                        .movie(Movie.builder().id(movieId).build())
                        .director(Person.builder().id(directorId).build())
                        .build()
        ).toList();
        for (MovieDirector movieDirector : movieDirectors) {
            if (!movieDirectorRepository.existsByMovieAndDirector(movieDirector.getMovie(), movieDirector.getDirector())) {
                movieDirectorRepository.save(movieDirector);
            }
        }
    }

}
