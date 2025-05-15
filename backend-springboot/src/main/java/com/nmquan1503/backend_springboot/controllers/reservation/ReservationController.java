package com.nmquan1503.backend_springboot.controllers.reservation;

import com.nmquan1503.backend_springboot.dtos.requests.reservation.ReservationCreationRequest;
import com.nmquan1503.backend_springboot.dtos.responses.ApiResponse;
import com.nmquan1503.backend_springboot.dtos.responses.reservation.ReservationDetailResponse;
import com.nmquan1503.backend_springboot.services.reservation.ReservationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationController {

    ReservationService reservationService;

    @PostMapping
    ResponseEntity<ApiResponse<ReservationDetailResponse>> createReservation(
            @RequestBody ReservationCreationRequest request
    ) {
        ApiResponse<ReservationDetailResponse> response = ApiResponse.success(
                reservationService.createReservation(request)
        );
        return ResponseEntity.ok().body(response);
    }

}
