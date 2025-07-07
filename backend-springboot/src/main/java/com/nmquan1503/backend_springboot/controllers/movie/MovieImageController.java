package com.nmquan1503.backend_springboot.controllers.movie;

import com.nmquan1503.backend_springboot.dtos.responses.ApiResponse;
import com.nmquan1503.backend_springboot.dtos.responses.movie.MovieImageResponse;
import com.nmquan1503.backend_springboot.services.movie.MovieImageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-images")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieImageController {

    MovieImageService movieImageService;

    @GetMapping("/movie/{movieId}")
    ResponseEntity<ApiResponse<Page<MovieImageResponse>>> getMovieImagesByMovieId(
            @PathVariable Long movieId,
            Pageable pageable
    ) {
        ApiResponse<Page<MovieImageResponse>> response = ApiResponse.success(
                movieImageService.getMovieImageByMovieId(movieId, pageable)
        );
        return ResponseEntity.ok().body(response);
    }

}
