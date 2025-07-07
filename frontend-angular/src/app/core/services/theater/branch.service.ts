import { Injectable } from "@angular/core";
import { API_BASE_URL } from "../../constants/api.constants";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { ApiResponse } from "../../models/responses/api-response.model";
import { BranchOptionResponse } from "../../models/responses/theater/branch-option-response.model";

@Injectable({
    providedIn: 'root'
})
class BranchService {

    private apiUrl = `${API_BASE_URL}/branches`;

    constructor(private http: HttpClient) { }

    getAllBranchOptions(): Observable<ApiResponse<BranchOptionResponse[]>> {
        return this.http.get<ApiResponse<BranchOptionResponse[]>>(
            `${this.apiUrl}/options`
        );
    }
}

export { BranchService };