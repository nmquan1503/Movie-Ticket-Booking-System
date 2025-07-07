package com.nmquan1503.backend_springboot.services.movie;

import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieCrewCreationRequest;
import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieCrewUpdateRequest;
import com.nmquan1503.backend_springboot.dtos.responses.movie.MovieCrewResponse;
import com.nmquan1503.backend_springboot.entities.movie.Movie;
import com.nmquan1503.backend_springboot.entities.movie.MovieCrew;
import com.nmquan1503.backend_springboot.entities.movie.Person;
import com.nmquan1503.backend_springboot.entities.movie.Position;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.mappers.movie.MovieCrewMapper;
import com.nmquan1503.backend_springboot.repositories.movie.MovieCrewRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.PackagePrivate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieCrewService {

    MovieCrewRepository movieCrewRepository;

    PersonService personService;
    PositionService positionService;

    MovieCrewMapper movieCrewMapper;

    public List<MovieCrewResponse> getCrewByMovieId(Long movieId) {
        return movieCrewMapper.toListMovieCrewResponse(
                movieCrewRepository.findByMovieId(movieId)
        );
    }

    public Page<MovieCrewResponse> getCrewByMovieId(Long movieId, Pageable pageable) {
        return movieCrewRepository.findByMovieId(movieId, pageable)
                .map(movieCrewMapper::toMovieCrewResponse);
    }

    @Transactional
    public void delete(Long movieId, List<Long> movieCrewIds) {
        List<Long> uniqueMovieCrewIds = movieCrewIds.stream().distinct().toList();
        if (uniqueMovieCrewIds.size() < movieCrewIds.size()) {
            throw new GeneralException(ResponseCode.DUPLICATE_MOVIE_CREW);
        }
        if (movieCrewRepository.deleteByMovieIdAndIdIn(movieId, movieCrewIds) != movieCrewIds.size()) {
            throw new GeneralException(ResponseCode.MOVIE_CREW_NOT_FOUND);
        }
    }

    @Transactional
    public void update(Long movieId, List<MovieCrewUpdateRequest> requests) {
        List<Integer> uniquePositionIds = requests.stream().map(MovieCrewUpdateRequest::getPositionId).distinct().toList();
        if (!positionService.allUniquePositionIdsExist(uniquePositionIds)) {
            throw new GeneralException(ResponseCode.POSITION_NOT_FOUND);
        }
        Map<Long, MovieCrewUpdateRequest> requestMap = new HashMap<>();
        for (MovieCrewUpdateRequest request : requests) {
            if (requestMap.containsKey(request.getId())) {
                throw new GeneralException(ResponseCode.DUPLICATE_MOVIE_CREW);
            }
            requestMap.put(request.getId(), request);
        }
        List<MovieCrew> crew = movieCrewRepository.findByMovieIdAndIdIn(movieId, requests.stream().map(MovieCrewUpdateRequest::getId).toList());
        if (crew.size() < requests.size()) {
            throw new GeneralException(ResponseCode.MOVIE_CREW_NOT_FOUND);
        }
        for (MovieCrew movieCrew : crew) {
            if (requestMap.containsKey(movieCrew.getId())) {
                MovieCrewUpdateRequest request = requestMap.get(movieCrew.getId());
                movieCrew.setPosition(Position.builder().id(request.getPositionId()).build());
            }
        }
        movieCrewRepository.saveAll(crew);
    }

    @Transactional
    public void save(Long movieId, List<MovieCrewCreationRequest> requests) {
        List<Long> uniquePersonIds = requests.stream().map(MovieCrewCreationRequest::getPersonId).distinct().toList();
        if (!personService.allUniquePersonIdsExist(uniquePersonIds)) {
            throw new GeneralException(ResponseCode.PERSON_NOT_FOUND);
        }

        List<Integer> uniquePositionIds = requests.stream().map(MovieCrewCreationRequest::getPositionId).distinct().toList();
        if (!positionService.allUniquePositionIdsExist(uniquePositionIds)) {
            throw new GeneralException(ResponseCode.POSITION_NOT_FOUND);
        }

        Movie fakeMovie = Movie.builder().id(movieId).build();
        List<MovieCrew> crew = requests.stream().map(
                request -> MovieCrew.builder()
                        .movie(fakeMovie)
                        .person(Person.builder().id(request.getPersonId()).build())
                        .position(Position.builder().id(request.getPositionId()).build())
                        .build()
        ).toList();

        movieCrewRepository.saveAll(crew);
    }

}
