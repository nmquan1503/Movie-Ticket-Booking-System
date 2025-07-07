import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ApiResponse } from "../../models/responses/api-response.model";
import { WardDetailResponse } from "../../models/responses/location/ward-detail-response.model";
import { API_BASE_URL } from "../../constants/api.constants";

@Injectable({
    providedIn: 'root'
})
class WardService {

    private apiUrl = `${API_BASE_URL}/wards`;

    constructor(private http:HttpClient) { }

    getAllWardDetails(): Observable<ApiResponse<WardDetailResponse[]>> {
        return this.http.get<ApiResponse<WardDetailResponse[]>>(
            `${this.apiUrl}/details`
        );
    }
}

export { WardService };