package com.nmquan1503.backend_springboot.controllers.theater;

import com.nmquan1503.backend_springboot.dtos.responses.ApiResponse;
import com.nmquan1503.backend_springboot.dtos.responses.theater.SeatDetailResponse;
import com.nmquan1503.backend_springboot.services.theater.SeatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SeatController {

    SeatService seatService;

    @GetMapping("/details/showtime/{showtimeId}")
    ResponseEntity<ApiResponse<List<SeatDetailResponse>>> getSeatDetailsByShowtimeId(
            @PathVariable Long showtimeId
    ) {
        ApiResponse<List<SeatDetailResponse>> response = ApiResponse.success(
                seatService.getSeatDetailsByShowtimeId(showtimeId)
        );
        return ResponseEntity.ok().body(response);
    }

}
