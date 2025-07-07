package com.nmquan1503.backend_springboot.services.movie;

import com.nmquan1503.backend_springboot.dtos.internal.MovieReviewStats;
import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieCreationRequest;
import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieUpdateRequest;
import com.nmquan1503.backend_springboot.dtos.responses.movie.*;
import com.nmquan1503.backend_springboot.entities.movie.Category;
import com.nmquan1503.backend_springboot.entities.movie.Movie;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.mappers.movie.CategoryMapper;
import com.nmquan1503.backend_springboot.mappers.movie.MovieMapper;
import com.nmquan1503.backend_springboot.repositories.movie.MovieRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieService {

    MovieRepository movieRepository;
    CategoryService categoryService;
    LanguageService languageService;
    AgeRatingService ageRatingService;
    MovieCategoryService movieCategoryService;
    MovieCastService movieCastService;
    MovieCrewService movieCrewService;
    MovieReviewService movieReviewService;
    MovieImageService movieImageService;

    MovieMapper movieMapper;
    CategoryMapper categoryMapper;

    public List<MoviePreviewResponse> getTrendingMoviePreviews() {
        List<Movie> trendingMovies = movieRepository.findTrendingMovies(5);
        return trendingMovies.stream().map(
                movieMapper::toMoviePreviewResponse
        ).toList();
    }

    public Page<MovieListItemResponse> getMovieListItems(Pageable pageable) {
        Page<Movie> movies = movieRepository.findAllSortByFinalScore(pageable);
        return convertToPageItem(movies);
    }

    public Page<MovieListItemResponse> getNowShowingMovieListItems(Pageable pageable) {
        Page<Movie> movies = movieRepository.findNowShowingSortByFinalScore(pageable);
        return convertToPageItem(movies);
    }

    public Page<MovieListItemResponse> getUpComingMovieListItems(Pageable pageable) {
        Page<Movie> movies = movieRepository.findUpComingSortByFinalScore(pageable);
        return convertToPageItem(movies);
    }

    private Page<MovieListItemResponse> convertToPageItem(Page<Movie> movies) {
        List<Long> movieIds = movies.getContent().stream().map(Movie::getId).toList();
        Map<Long, MovieReviewStats> statsMap = movieReviewService.getMapStatsByMovieIds(movieIds);
        Map<Long, List<Category>> categoriesMap = movieCategoryService.fetchCategoryByMovieIds(movieIds);
        return movies.map(
                movie -> {
                    MovieListItemResponse response = movieMapper.toMovieListItemResponse(movie);
                    MovieReviewStats stats = statsMap.get(response.getId());
                    if (stats == null) {
                        response.setAverageRating(0.0);
                        response.setRatingCount((long)0);
                    }
                    else {
                        response.setAverageRating(stats.getAverageRating());
                        response.setRatingCount(stats.getCountRating());
                    }
                    List<Category> categories = categoriesMap.get(response.getId());
                    if (categories != null) {
                        response.setCategories(categoryMapper.toListCategoryResponse(categories));
                    }
                    return  response;
                }
        );
    }

    public MovieDetailResponse getMovieDetailByMovieId(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new GeneralException(ResponseCode.MOVIE_NOT_FOUND));
        MovieDetailResponse response = movieMapper.toMovieDetailResponse(movie);
        addToMovieDetailResponse(
                response,
                true,
                true,
                true,
                true,
                true
        );
        return response;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public MovieDetailResponse createMovie(MovieCreationRequest request) {
        Movie movie = movieMapper.toMovie(request);
        movie.setOriginalLanguage(languageService.fetchLanguageById(request.getOriginalLanguageId()));
        movie.setAgeRating(ageRatingService.fetchAgeRatingById(request.getAgeRatingId()));
        movie = movieRepository.save(movie);
        movieCategoryService.save(movie.getId(), request.getCategoryIds());
        movieCastService.save(movie.getId(), request.getCast(), true);
        movieCrewService.save(movie.getId(), request.getCrew());

        MovieDetailResponse response = movieMapper.toMovieDetailResponse(movie);
        addToMovieDetailResponse(
                response,
                true,
                true,
                true,
                true,
                true
        );
        return response;
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
        if (request.getAgeRatingId() != null) {
            movie.setAgeRating(ageRatingService.fetchAgeRatingById(request.getAgeRatingId()));
        }
        movie = movieRepository.save(movie);

        movieCategoryService.delete(movieId, request.getDeleteCategoryIds());
        movieCategoryService.save(movie.getId(), request.getAddCategoryIds());

        movieCastService.delete(movieId, request.getDeleteCast(), false);
        movieCastService.update(movieId, request.getUpdateCast(), false);
        movieCastService.save(movieId, request.getCreateCast(), true);

        movieCrewService.delete(movieId, request.getDeleteCrew());
        movieCrewService.update(movieId, request.getUpdateCrew());
        movieCrewService.save(movieId, request.getCreateCrew());

        MovieDetailResponse response = movieMapper.toMovieDetailResponse(movie);
        addToMovieDetailResponse(
                response,
                true,
                true,
                true,
                true,
                true
        );
        return response;
    }

    private void addToMovieDetailResponse(
            MovieDetailResponse response,
            boolean addStats,
            boolean addCast,
            boolean addCrew,
            boolean addImages,
            boolean addCategories
    ) {
        Long movieId = response.getId();
        if (addStats) {
            MovieReviewStats stats = movieReviewService.getStatsByMovieId(movieId);
            response.setAverageRating(stats.getAverageRating());
            response.setRatingCount(stats.getCountRating());
        }
        if (addCast) {
            response.setCast(movieCastService.getCastByMovieId(movieId, PageRequest.of(0, 10)));
        }
        if (addCrew) {
            response.setCrew(movieCrewService.getCrewByMovieId(movieId, PageRequest.of(0, 10)));
        }
        if (addImages) {
            response.setImages(movieImageService.getMovieImageByMovieId(movieId, PageRequest.of(0, 5)));
        }
        if (addCategories) {
            response.setCategories(categoryService.getCategoriesByMovieId(movieId));
        }
    }

    public boolean existsByMovieId(Long movieId) {
        return movieRepository.existsById(movieId);
    }

    public Movie fetchByMovieId(Long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new GeneralException(ResponseCode.MOVIE_NOT_FOUND));
    }


    public List<Long> fetchMovieIdsByListIds(List<Long> ids) {
        return movieRepository.findIdsByListIds(ids);
    }

    public MovieBannerResponse getMovieBanner(Long movieId) {
        return movieMapper.toMovieBannerResponse(
                movieRepository.findById(movieId).orElseThrow(() -> new GeneralException(ResponseCode.MOVIE_NOT_FOUND))
        );
    }

}
