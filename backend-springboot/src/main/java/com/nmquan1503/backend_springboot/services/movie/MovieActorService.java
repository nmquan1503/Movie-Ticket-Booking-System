package com.nmquan1503.backend_springboot.services.movie;

import com.nmquan1503.backend_springboot.entities.movie.Movie;
import com.nmquan1503.backend_springboot.entities.movie.MovieActor;
import com.nmquan1503.backend_springboot.entities.movie.MovieDirector;
import com.nmquan1503.backend_springboot.entities.movie.Person;
import com.nmquan1503.backend_springboot.repositories.movie.MovieActorRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieActorService {

    MovieActorRepository movieActorRepository;

    public void save(Long movieId, List<Long> actorIds) {
        if (movieId == null || actorIds == null) {
            return;
        }
        movieActorRepository.deleteAllByMovieIdAndActorIdNotIn(movieId, actorIds);
        List<MovieActor> movieActors = actorIds.stream().map(
                actorId -> MovieActor.builder()
                        .movie(Movie.builder().id(movieId).build())
                        .actor(Person.builder().id(actorId).build())
                        .build()
        ).toList();
        for (MovieActor movieActor : movieActors) {
            if (!movieActorRepository.existsByMovieAndActor(movieActor.getMovie(), movieActor.getActor())) {
                movieActorRepository.save(movieActor);
            }
        }
    }

}
