import { Injectable } from "@angular/core";
import { API_BASE_URL } from "../../constants/api.constants";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { ApiResponse } from "../../models/responses/api-response.model";
import { TicketPriceResponse } from "../../models/responses/ticket/ticket-price-response.model";

@Injectable({
    providedIn: 'root'
})
export class TicketPriceService {

    private apiUrl = `${API_BASE_URL}/ticket-price`;
    
    constructor(private http: HttpClient) { }

    getAllBaseTicketPrice(): Observable<ApiResponse<TicketPriceResponse[]>> {
        return this.http.get<ApiResponse<TicketPriceResponse[]>>(
            this.apiUrl
        );
    }

}