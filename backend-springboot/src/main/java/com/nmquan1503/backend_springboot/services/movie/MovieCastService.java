package com.nmquan1503.backend_springboot.services.movie;

import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieCastCreationRequest;
import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieCastUpdateRequest;
import com.nmquan1503.backend_springboot.dtos.responses.movie.MovieCastResponse;
import com.nmquan1503.backend_springboot.entities.movie.Movie;
import com.nmquan1503.backend_springboot.entities.movie.MovieCast;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.mappers.movie.MovieCastMapper;
import com.nmquan1503.backend_springboot.repositories.movie.MovieCastRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieCastService {

    MovieCastRepository movieCastRepository;

    MovieCastMapper movieCastMapper;

    public List<MovieCastResponse> getCastByMovieId(Long movieId) {
        return movieCastMapper.toListMovieCastResponse(
                movieCastRepository.findByMovieIdOrderByOrderAsc(movieId)
        );
    }

    public Page<MovieCastResponse> getCastByMovieId(Long movieId, Pageable pageable) {
        return movieCastRepository.findByMovieIdOrderByOrderAsc(movieId, pageable)
                .map(movieCastMapper::toMovieCastResponse);
    }

    public List<MovieCast> fetchByMovieId(Long movieId) {
        return movieCastRepository.findByMovieIdOrderByOrderAsc(movieId);
    }

    @Transactional
    public void delete(Long movieId, List<Long> movieCastIds, boolean normalize) {
        List<Long> uniqueMovieCastIds = movieCastIds.stream().distinct().toList();
        if (uniqueMovieCastIds.size() < movieCastIds.size()) {
            throw new GeneralException(ResponseCode.DUPLICATE_MOVIE_CAST);
        }
        if (movieCastRepository.deleteByMovieIdAndIdIn(movieId, movieCastIds) != movieCastIds.size()) {
            throw new GeneralException(ResponseCode.MOVIE_CAST_NOT_FOUND);
        }
        if (normalize) {
            List<MovieCast> cast = movieCastRepository.findByMovieIdOrderByOrderAsc(movieId);
            radixSortCast(cast);
            for (int i = 0; i < cast.size(); i++) {
                cast.get(i).setOrder((short)i);
            }
            movieCastRepository.saveAll(cast);
        }
    }

    @Transactional
    public void update(Long movieId, List<MovieCastUpdateRequest> requests, boolean normalize) {
        if (movieId == null || requests == null || requests.isEmpty()) {
            return;
        }
        Map<Long, MovieCastUpdateRequest> requestMap = new HashMap<>();
        for (MovieCastUpdateRequest request : requests) {
            if (requestMap.containsKey(request.getId())) {
                throw new GeneralException(ResponseCode.DUPLICATE_MOVIE_CAST);
            }
            requestMap.put(request.getId(), request);
        }
        List<MovieCast> origin = movieCastRepository.findByMovieIdOrderByOrderAsc(movieId);
        int countUpdate = 0;
        for (MovieCast movieCast : origin) {
            if (requestMap.containsKey(movieCast.getId())) {
                countUpdate ++;
                movieCastMapper.updateMovieCast(movieCast, requestMap.get(movieCast.getId()));
            }
        }
        if (countUpdate < requests.size()) {
            throw new GeneralException(ResponseCode.MOVIE_CAST_NOT_FOUND);
        }
        radixSortCast(origin);
        for (int i = 1; i < origin.size(); i++) {
            if (origin.get(i).getOrder().equals(origin.get(i-1).getOrder())) {
                throw new GeneralException(ResponseCode.DUPLICATE_CAST_ORDER);
            }
        }
        if (normalize) {
            for (int i = 0; i < origin.size(); i++) {
                origin.get(i).setOrder((short)i);
            }
        }
        movieCastRepository.saveAll(origin);
    }

    @Transactional
    public void save(Long movieId, List<MovieCastCreationRequest> requests, boolean normalize) {
        if (requests == null || requests.isEmpty()) {
            return;
        }
        radixSortMovieCastCreationRequests(requests);
        for (int i = 1; i < requests.size(); i++) {
            if (requests.get(i).getOrder().equals(requests.get(i-1).getOrder())) {
                throw new GeneralException(ResponseCode.DUPLICATE_CAST_ORDER);
            }
        }
        if (normalize) {
            for (int i = 0; i < requests.size(); i++) {
                requests.get(i).setOrder((short)i);
            }
        }
        List<MovieCast> cast = movieCastMapper.toListMovieCast(requests);
        Movie fakeMovie = Movie.builder().id(movieId).build();
        for (MovieCast movieCast : cast) {
            movieCast.setMovie(fakeMovie);
        }
        movieCastRepository.saveAll(cast);
    }

    private void radixSortCast(List<MovieCast> cast) {
        int maxOrder = 0;
        for (MovieCast movieCast : cast) {
            if (movieCast.getOrder() < 0) {
                throw new GeneralException(ResponseCode.CAST_ORDER_INVALID);
            }
            maxOrder = movieCast.getOrder() > maxOrder ? movieCast.getOrder() : maxOrder;
        }
        for (int exp = 1; maxOrder / exp > 0; exp *= 10) {
            MovieCast[] out = new MovieCast[cast.size()];
            int[] count = new int[10];
            for (MovieCast movieCast : cast) {
                int digit = (movieCast.getOrder() / exp) % 10;
                count[digit]++;
            }
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }
            for (int i = cast.size() - 1; i >= 0; i--) {
                int digit = (cast.get(i).getOrder() / exp) % 10;
                out[count[digit] - 1] = cast.get(i);
                count[digit]--;
            }
            for (int i = 0; i < cast.size(); i++) {
                cast.set(i, out[i]);
            }
        }
    }

    private void radixSortMovieCastCreationRequests(List<MovieCastCreationRequest> requests) {
        int maxOrder = 0;
        for (MovieCastCreationRequest request : requests) {
            if (request.getOrder() < 0) {
                throw new GeneralException(ResponseCode.CAST_ORDER_INVALID);
            }
            maxOrder = request.getOrder() > maxOrder ? request.getOrder() : maxOrder;
        }
        for (int exp = 1; maxOrder / exp > 0; exp *= 10) {
            MovieCastCreationRequest[] out = new MovieCastCreationRequest[requests.size()];
            int[] count = new int[10];
            for (MovieCastCreationRequest request : requests) {
                int digit = (request.getOrder() / exp) % 10;
                count[digit]++;
            }
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }
            for (int i = requests.size() - 1; i >= 0; i--) {
                int digit = (requests.get(i).getOrder() / exp) % 10;
                out[count[digit] - 1] = requests.get(i);
                count[digit]--;
            }
            for (int i = 0; i < requests.size(); i++) {
                requests.set(i, out[i]);
            }
        }
    }

}
