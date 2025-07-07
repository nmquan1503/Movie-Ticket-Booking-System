package com.nmquan1503.backend_springboot.controllers.theater;

import com.nmquan1503.backend_springboot.dtos.responses.ApiResponse;
import com.nmquan1503.backend_springboot.dtos.responses.theater.RoomTypeDetailResponse;
import com.nmquan1503.backend_springboot.dtos.responses.theater.RoomTypeIconResponse;
import com.nmquan1503.backend_springboot.dtos.responses.theater.RoomTypePreviewResponse;
import com.nmquan1503.backend_springboot.dtos.responses.theater.RoomTypeSimpleResponse;
import com.nmquan1503.backend_springboot.services.theater.RoomTypeService;
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
@RequestMapping("/room-types")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomTypeController {

    RoomTypeService roomTypeService;

    @GetMapping("/previews")
    ResponseEntity<ApiResponse<List<RoomTypePreviewResponse>>> getAllRoomTypePreviews() {
        ApiResponse<List<RoomTypePreviewResponse>> response = ApiResponse.success(
                roomTypeService.getAllRoomTypePreviews()
        );
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/icons")
    ResponseEntity<ApiResponse<List<RoomTypeIconResponse>>> getAllRoomTypeIcons() {
        ApiResponse<List<RoomTypeIconResponse>> response = ApiResponse.success(
                roomTypeService.getAllRoomTypeIcons()
        );
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/details/{roomTypeId}")
    ResponseEntity<ApiResponse<RoomTypeDetailResponse>> getRoomTypeDetailById(
            @PathVariable Byte roomTypeId
    ) {
        ApiResponse<RoomTypeDetailResponse> response = ApiResponse.success(
                roomTypeService.getRoomTypeDetailById(roomTypeId)
        );
        return ResponseEntity.ok().body(response);
    }

}
