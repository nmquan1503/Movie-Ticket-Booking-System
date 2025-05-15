package com.nmquan1503.backend_springboot.controllers.movie;

import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieReviewCreationRequest;
import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieReviewUpdateRequest;
import com.nmquan1503.backend_springboot.dtos.responses.ApiResponse;
import com.nmquan1503.backend_springboot.dtos.responses.movie.MovieReviewResponse;
import com.nmquan1503.backend_springboot.services.movie.MovieReviewService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie-reviews")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieReviewController {

    MovieReviewService movieReviewService;

    @GetMapping("/movie/{movieId}")
    ResponseEntity<ApiResponse<Page<MovieReviewResponse>>> getMovieReviewPage(@PathVariable Long movieId, Pageable pageable) {
        ApiResponse<Page<MovieReviewResponse>> response = ApiResponse.success(movieReviewService.getMovieReviewSummaryPage(movieId, pageable));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    ResponseEntity<ApiResponse<MovieReviewResponse>> createReview(@RequestBody MovieReviewCreationRequest request) {
        ApiResponse<MovieReviewResponse> response = ApiResponse.success(movieReviewService.createReview(request));
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{movieReviewId}")
    public ResponseEntity<ApiResponse<MovieReviewResponse>> updateReview(
            @PathVariable Long movieReviewId,
            @RequestBody MovieReviewUpdateRequest request
    ) {
        ApiResponse<MovieReviewResponse> response = ApiResponse.success(movieReviewService.updateReview(movieReviewId, request));
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{movieReviewId}")
    public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable Long movieReviewId) {
        movieReviewService.deleteReview(movieReviewId);
        ApiResponse<Void> response = ApiResponse.success(null);
        return ResponseEntity.ok().body(response);
    }

}
