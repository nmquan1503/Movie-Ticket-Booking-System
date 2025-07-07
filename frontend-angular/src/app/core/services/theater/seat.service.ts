import { Injectable } from "@angular/core";
import { API_BASE_URL } from "../../constants/api.constants";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { ApiResponse } from "../../models/responses/api-response.model";
import { SeatDetailResponse } from "../../models/responses/theater/seat-detail-response.model";

@Injectable({
    providedIn: 'root'
})
class SeatService {

    private apiUrl = `${API_BASE_URL}/seats`;

    constructor(private http: HttpClient) { }

    getSeatDetailsByShowtimeId(
        showtimeId: number
    ): Observable<ApiResponse<SeatDetailResponse[]>> {
        return this.http.get<ApiResponse<SeatDetailResponse[]>>(
            `${this.apiUrl}/details/showtime/${showtimeId}`
        );
    }
}

export { SeatService };