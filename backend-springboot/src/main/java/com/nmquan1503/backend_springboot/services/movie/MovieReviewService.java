package com.nmquan1503.backend_springboot.services.movie;

import com.nmquan1503.backend_springboot.dtos.internal.MovieReviewStats;
import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieReviewCreationRequest;
import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieReviewUpdateRequest;
import com.nmquan1503.backend_springboot.dtos.responses.movie.MovieReviewResponse;
import com.nmquan1503.backend_springboot.entities.movie.Movie;
import com.nmquan1503.backend_springboot.entities.movie.MovieReview;
import com.nmquan1503.backend_springboot.entities.user.User;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.mappers.movie.MovieReviewMapper;
import com.nmquan1503.backend_springboot.repositories.movie.MovieReviewRepository;
import com.nmquan1503.backend_springboot.services.authentication.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieReviewService {

    MovieReviewRepository movieReviewRepository;
    AuthenticationService authenticationService;

    MovieReviewMapper movieReviewMapper;

    public Page<MovieReviewResponse> getMovieReviewSummaryPage(Long movieId, Pageable pageable) {
        Page<MovieReview> movieReviews = movieReviewRepository.findByMovieId(movieId, pageable);
        return movieReviews.map(movieReviewMapper::toMovieReviewSummaryResponse);
    }

    @Transactional
    public MovieReviewResponse createReview(MovieReviewCreationRequest request) {
        Long userId = authenticationService.getCurrentUserId();
        MovieReview movieReview = movieReviewMapper.toMovieReview(request);
        movieReview.setUser(User.builder().id(userId).build());
        movieReview.setMovie(Movie.builder().id(request.getMovieId()).build());
        movieReview = movieReviewRepository.save(movieReview);
        return movieReviewMapper.toMovieReviewSummaryResponse(movieReview);
    }

    @Transactional
    public MovieReviewResponse updateReview(Long movieReviewId, MovieReviewUpdateRequest request) {
        Long userId = authenticationService.getCurrentUserId();
        MovieReview oldReview = fetchMovieReviewById(movieReviewId);
        if (!userId.equals(oldReview.getUser().getId())) {
            throw new GeneralException(ResponseCode.UNAUTHORIZED);
        }
        MovieReview newReview = movieReviewMapper.clone(oldReview);
        movieReviewMapper.updateReview(newReview, request);
        newReview.setCreationTime(LocalDateTime.now());
        movieReviewRepository.save(newReview);
        return movieReviewMapper.toMovieReviewSummaryResponse(newReview);
    }

    @Transactional
    public void deleteReview(Long movieReviewId) {
        Long userId = authenticationService.getCurrentUserId();
        MovieReview review = fetchMovieReviewById(movieReviewId);
        if (!userId.equals(review.getUser().getId())) {
            throw new GeneralException(ResponseCode.UNAUTHORIZED);
        }
        movieReviewRepository.delete(review);
    }

    public MovieReview fetchMovieReviewById(Long movieReviewId) {
        return movieReviewRepository.findById(movieReviewId)
                .orElseThrow(() -> new GeneralException(ResponseCode.MOVIE_REVIEW_NOT_FOUND));
    }

    public MovieReviewStats getStatsByMovieId(Long movieId) {
        return movieReviewRepository.findMovieReviewStatsByMovieId(movieId);
    }

    public Map<Long, MovieReviewStats> getMapStatsByMovieIds(List<Long> movieIds) {
        return movieReviewRepository.findListMovieReviewsStatsByMovieIds(movieIds);
    }

    public List<MovieReview> fetchByMovieId(Long movieId) {
        return movieReviewRepository.findByMovieId(movieId);
    }

    public List<MovieReview> fetchMovieReviewWithinPeriod(Long movieId, LocalDateTime from, LocalDateTime to) {
        return movieReviewRepository.findByMovieIdAndCreationTimeGreaterThanEqualAndCreationTimeLessThan(movieId, from, to);
    }

}
