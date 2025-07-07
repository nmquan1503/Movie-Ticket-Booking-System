import { Injectable } from "@angular/core";
import { API_BASE_URL } from "../../constants/api.constants";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { ApiResponse } from "../../models/responses/api-response.model";
import { ShowtimeOptionResponse } from "../../models/responses/showtime/showtime-option-response.model";
import { ShowtimeCreationRequest } from "../../models/requests/showtime/showtime-creation-request.model";
import { ShowtimeUpdateRequest } from "../../models/requests/showtime/showtime-update-request.model";
import { ShowtimeDetailResponse } from "../../models/responses/showtime/showtime-detail-response.model";

@Injectable({
    providedIn: 'root'
})
class ShowtimeService {

    private apiUrl = `${API_BASE_URL}/showtimes`;

    constructor(private http: HttpClient) { }

    getShowtimeDetail(
        showtimeId: number
    ): Observable<ApiResponse<ShowtimeDetailResponse>> {
        return this.http.get<ApiResponse<ShowtimeDetailResponse>>(
            `${this.apiUrl}/detail/${showtimeId}`
        );
    }

    getShowtimeOptionByMovieId(
        movieId: number
    ): Observable<ApiResponse<ShowtimeOptionResponse[]>> {
        return this.http.get<ApiResponse<ShowtimeOptionResponse[]>>(
            `${this.apiUrl}/movie/${movieId}`
        );
    }

    createShowtime(
        request: ShowtimeCreationRequest
    ): Observable<ApiResponse<void>> {
        return this.http.post<ApiResponse<void>>(
            this.apiUrl, 
            request
        );
    }

    updateShowtime(
        showtimeId: number, 
        request: ShowtimeUpdateRequest
    ): Observable<ApiResponse<void>> {
        return this.http.put<ApiResponse<void>>(
            `${this.apiUrl}/${showtimeId}`, 
            request
        );
    }
}

export { ShowtimeService };