package com.nmquan1503.backend_springboot.controllers.movie;

import com.nmquan1503.backend_springboot.dtos.requests.movie.MovieViewHistoryCreationRequest;
import com.nmquan1503.backend_springboot.dtos.responses.ApiResponse;
import com.nmquan1503.backend_springboot.services.movie.MovieViewHistoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.kafka.shaded.com.google.protobuf.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-view-history")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieViewHistoryController {

    MovieViewHistoryService movieViewHistoryService;

    @PostMapping
    ResponseEntity<ApiResponse<Object>> createView(MovieViewHistoryCreationRequest request) {
        movieViewHistoryService.createView(request);
        ApiResponse<Object> response = ApiResponse.success(null);
        return ResponseEntity.ok().body(response);
    }

}
