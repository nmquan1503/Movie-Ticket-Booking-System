import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { API_BASE_URL } from "../../constants/api.constants";
import { ApiResponse } from "../../models/responses/api-response.model";
import { ProvinceDetailResponse } from "../../models/responses/location/province-detail-response.model";

@Injectable({
    providedIn: 'root'
})
export class ProvinceService {

    private apiUrl = `${API_BASE_URL}/provinces`;

    constructor(private http: HttpClient) { }
    
    getAllProvinceDetails(): Observable<ApiResponse<ProvinceDetailResponse[]>> {
        return this.http.get<ApiResponse<ProvinceDetailResponse[]>>(
            `${this.apiUrl}/details`
        );
    }
}