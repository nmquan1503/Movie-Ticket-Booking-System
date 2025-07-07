import { Injectable } from "@angular/core";
import { API_BASE_URL } from "../../constants/api.constants";
import { HttpClient } from "@angular/common/http";
import { BranchProductCreationRequest } from "../../models/requests/product/branch-product-creation-request.model";
import { Observable } from "rxjs";
import { ApiResponse } from "../../models/responses/api-response.model";
import { BranchProductUpdateRequest } from "../../models/requests/product/branch-product-update-request.model";

@Injectable({
    providedIn: 'root'
})
class BranchProductService {

    private apiUrl = `${API_BASE_URL}/branch-product`;

    constructor(private http: HttpClient) { }

    createBranchProduct(
        request: BranchProductCreationRequest
    ): Observable<ApiResponse<void>> {
        return this.http.post<ApiResponse<void>>(
            this.apiUrl, 
            request
        );
    }

    updateBranchProduct(
        branchProductId: number, 
        request: BranchProductUpdateRequest
    ): Observable<ApiResponse<void>> {
        return this.http.put<ApiResponse<void>>(
            `${this.apiUrl}/${branchProductId}`, 
            request
        );
    }
}

export { BranchProductService };