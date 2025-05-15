package com.nmquan1503.backend_springboot.controllers.movie;

import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieCreationRequest;
import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieUpdateRequest;
import com.nmquan1503.backend_springboot.dtos.responses.ApiResponse;
import com.nmquan1503.backend_springboot.dtos.responses.movie.MovieListItemResponse;
import com.nmquan1503.backend_springboot.dtos.responses.movie.MovieDetailResponse;
import com.nmquan1503.backend_springboot.dtos.responses.movie.MoviePreviewResponse;
import com.nmquan1503.backend_springboot.services.movie.MovieService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieController {

    MovieService movieService;

    @GetMapping("/previews/trending")
    ResponseEntity<ApiResponse<List<MoviePreviewResponse>>> getTrendingMoviePreviews() {
        ApiResponse<List<MoviePreviewResponse>> response = ApiResponse.success(movieService.getTrendingMoviePreviews());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/list")
    ResponseEntity<ApiResponse<Page<MovieListItemResponse>>> getMovieListItems(
            Pageable pageable
    ) {
        ApiResponse<Page<MovieListItemResponse>> response = ApiResponse.success(
                movieService.getMovieListItems(pageable)
        );
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/details/{movieId}")
    ResponseEntity<ApiResponse<MovieDetailResponse>> getMovieDetailByMovieId(
            @PathVariable Long movieId
    ) {
        ApiResponse<MovieDetailResponse> response = ApiResponse.success(
                movieService.getMovieDetailByMovieId(movieId)
        );
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    ResponseEntity<ApiResponse<Void>> createMovie(
            @RequestBody MovieCreationRequest request
    ) {
        movieService.createMovie(request);
        ApiResponse<Void> response = ApiResponse.success(null);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/{movieId}")
    ResponseEntity<ApiResponse<MovieDetailResponse>> updateMovie(
            @PathVariable Long movieId,
            @RequestBody MovieUpdateRequest request
    ) {
        ApiResponse<MovieDetailResponse> response = ApiResponse.success(
                movieService.updateMovie(movieId, request)
        );
        return ResponseEntity.ok().body(response);
    }

}
