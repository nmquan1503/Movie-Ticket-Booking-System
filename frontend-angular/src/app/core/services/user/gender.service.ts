import { Injectable } from "@angular/core";
import { API_BASE_URL } from "../../constants/api.constants";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { ApiResponse } from "../../models/responses/api-response.model";
import { GenderOptionResponse } from "../../models/responses/user/gender-option-response.model";

@Injectable({
    providedIn: 'root'
})
class GenderService {

    private apiUrl = `${API_BASE_URL}/genders`;

    constructor(private http: HttpClient) { }

    getAllGenderOptions(): Observable<ApiResponse<GenderOptionResponse[]>> {
        return this.http.get<ApiResponse<GenderOptionResponse[]>>(
            `${this.apiUrl}/options`
        );
    }
}

export { GenderService };