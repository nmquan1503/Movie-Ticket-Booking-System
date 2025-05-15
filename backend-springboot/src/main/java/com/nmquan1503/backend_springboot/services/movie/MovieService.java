package com.nmquan1503.backend_springboot.services.movie;

import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieCreationRequest;
import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieUpdateRequest;
import com.nmquan1503.backend_springboot.dtos.responses.movie.*;
import com.nmquan1503.backend_springboot.entities.movie.Movie;
import com.nmquan1503.backend_springboot.entities.movie.MovieReview;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.mappers.movie.MovieMapper;
import com.nmquan1503.backend_springboot.repositories.movie.MovieRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieService {

    MovieRepository movieRepository;
    PersonService personService;
    CategoryService categoryService;
    LanguageService languageService;
    AgeRatingService ageRatingService;
    MovieCategoryService movieCategoryService;
    MovieActorService movieActorService;
    MovieDirectorService movieDirectorService;

    MovieMapper movieMapper;

    public List<MoviePreviewResponse> getTrendingMoviePreviews() {
        List<Movie> trendingMovies = movieRepository.findTrendingMovies(5);
        return trendingMovies.stream().map(
                movieMapper::toMoviePreviewResponse
        ).toList();
    }

    public Page<MovieListItemResponse> getMovieListItems(Pageable pageable) {
        Page<Movie> movies = movieRepository.findAll(pageable);
        return movies.map(
                movieMapper::toMovieListItemResponse
        );
    }

    public MovieDetailResponse getMovieDetailByMovieId(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new GeneralException(ResponseCode.MOVIE_NOT_FOUND));
        List<PersonPreviewResponse> actors = personService.getActorPreviewsByMovieId(movieId);
        List<PersonPreviewResponse> directors = personService.getDirectorPreviewsByMovieId(movieId);
        List<CategoryResponse> categories = categoryService.getCategoriesByMovieId(movieId);
        MovieDetailResponse response = movieMapper.toMovieDetailResponse(movie);
        response.setActors(actors);
        response.setDirectors(directors);
        response.setCategories(categories);
        return response;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void createMovie(MovieCreationRequest request) {
        Movie movie = movieMapper.toMovie(request);
        movie.setOriginalLanguage(languageService.fetchLanguageById(request.getOriginalLanguageId()));
        movie.setSubtitleLanguage(languageService.fetchLanguageById(request.getSubtitleLanguageId()));
        movie.setAgeRating(ageRatingService.fetchAgeRatingById(request.getAgeRatingId()));
        movie = movieRepository.save(movie);
        movieCategoryService.save(movie.getId(), request.getCategoryIds());
        movieActorService.save(movie.getId(), request.getActorIds());
        movieDirectorService.save(movie.getId(), request.getDirectorIds());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public MovieDetailResponse updateMovie(Long movieId, MovieUpdateRequest request) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new GeneralException(ResponseCode.MOVIE_NOT_FOUND));
        movieMapper.updateMovie(movie, request);
        if (request.getOriginalLanguageId() != null) {
            movie.setOriginalLanguage(languageService.fetchLanguageById(request.getOriginalLanguageId()));
        }
        if (request.getSubtitleLanguageId() != null) {
            movie.setSubtitleLanguage(languageService.fetchLanguageById(request.getSubtitleLanguageId()));
        }
        if (request.getAgeRatingId() != null) {
            movie.setAgeRating(ageRatingService.fetchAgeRatingById(request.getAgeRatingId()));
        }
        movie = movieRepository.save(movie);
        movieCategoryService.save(movie.getId(), request.getCategoryIds());
        movieActorService.save(movie.getId(), request.getActorIds());
        movieDirectorService.save(movie.getId(), request.getDirectorIds());
        MovieDetailResponse response = movieMapper.toMovieDetailResponse(movie);
        response.setCategories(categoryService.getCategoriesByIds(request.getCategoryIds()));
        response.setActors(personService.getPersonPreviewsByIds(request.getActorIds()));
        response.setDirectors(personService.getPersonPreviewsByIds(request.getDirectorIds()));
        return response;
    }

    public void updateRatingAfterCreateReview(MovieReview review) {
        Movie movie = review.getMovie();
        movie.setAverageRating((movie.getAverageRating() * movie.getRatingCount() + review.getRating()) / (movie.getRatingCount() + 1));
        movie.setRatingCount(movie.getRatingCount()+1);
        movieRepository.save(movie);
    }

    public void updateRatingAfterUpdateReview(MovieReview oldReview, MovieReview newReview) {
        Movie movie = oldReview.getMovie();
        movie.setAverageRating((movie.getAverageRating() * movie.getRatingCount() - oldReview.getRating() + newReview.getRating()) / movie.getRatingCount());
        movieRepository.save(movie);
    }

    public void updateRatingAfterDeleteReview(MovieReview review) {
        Movie movie = review.getMovie();
        movie.setAverageRating((movie.getAverageRating() * movie.getRatingCount() - review.getRating()) / (movie.getRatingCount() - 1));
        movie.setRatingCount(movie.getRatingCount() - 1);
        movieRepository.save(movie);
    }

    public boolean existsByMovieId(Long movieId) {
        return movieRepository.existsById(movieId);
    }

    public Movie fetchByMovieId(Long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new GeneralException(ResponseCode.MOVIE_NOT_FOUND));
    }

}
