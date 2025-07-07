import { Injectable } from "@angular/core";
import { API_BASE_URL } from "../../constants/api.constants";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { ApiResponse } from "../../models/responses/api-response.model";
import { TicketDetailResponse } from "../../models/responses/ticket/ticket-detail-response.model";

@Injectable({
    providedIn: 'root'
})
class TicketService {
    
    private apiUrl = `${API_BASE_URL}/tickets`;

    constructor(private http: HttpClient) { }

    getTicketDetail(
        ticketId: number
    ): Observable<ApiResponse<TicketDetailResponse>> {
        return this.http.get<ApiResponse<TicketDetailResponse>>(
            `${this.apiUrl}/details/${ticketId}`
        );
    }
}

export { TicketService };