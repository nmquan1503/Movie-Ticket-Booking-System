package com.nmquan1503.backend_springboot.controllers.movie;

import com.nmquan1503.backend_springboot.dtos.responses.ApiResponse;
import com.nmquan1503.backend_springboot.dtos.responses.movie.MovieCrewResponse;
import com.nmquan1503.backend_springboot.services.movie.MovieCrewService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.coyote.Response;
import org.apache.kafka.shaded.com.google.protobuf.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-crew")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieCrewController {

    MovieCrewService movieCrewService;

    @GetMapping("/movie/{movieId}")
    ResponseEntity<ApiResponse<Page<MovieCrewResponse>>> getCrewByMovieId(
            @PathVariable Long movieId,
            Pageable pageable
    ) {
        ApiResponse<Page<MovieCrewResponse>> response = ApiResponse.success(
                movieCrewService.getCrewByMovieId(movieId, pageable)
        );
        return ResponseEntity.ok().body(response);
    }

}
