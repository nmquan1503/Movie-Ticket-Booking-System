import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { API_BASE_URL } from "../../constants/api.constants";
import { Observable } from "rxjs";
import { ApiResponse } from "../../models/responses/api-response.model";
import { RoomTypePreviewResponse } from "../../models/responses/theater/room-type-preview.model";
import { RoomTypeIconResponse } from "../../models/responses/theater/room-type-icon-response.model";
import { RoomTypeDetailResponse } from "../../models/responses/theater/room-type-detail-response.model";

@Injectable({
    providedIn: 'root'
})
export class RoomTypeService {

    private apiUrl = `${API_BASE_URL}/room-types`;

    constructor(
        private http: HttpClient
    ) { }

    getAllRoomTypePreviews(): Observable<ApiResponse<RoomTypePreviewResponse[]>> {
        return this.http.get<ApiResponse<RoomTypePreviewResponse[]>>(
            `${this.apiUrl}/previews`
        );
    }

    getAllRoomTypeIcons(): Observable<ApiResponse<RoomTypeIconResponse[]>> {
        return this.http.get<ApiResponse<RoomTypeIconResponse[]>>(
            `${this.apiUrl}/icons`
        );
    }

    getRoomTypeDetailById(
        roomTypeId: number
    ): Observable<ApiResponse<RoomTypeDetailResponse>> {
        return this.http.get<ApiResponse<RoomTypeDetailResponse>>(
            `${this.apiUrl}/details/${roomTypeId}`
        );
    }

}