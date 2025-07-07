import { Injectable } from "@angular/core";
import { API_BASE_URL } from "../../constants/api.constants";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { ApiResponse } from "../../models/responses/api-response.model";
import { ProductDetailResponse } from "../../models/responses/product/product-detail-response.model";
import { ProductCreationRequest } from "../../models/requests/product/product-creation-request.model";
import { ProductUpdateRequest } from "../../models/requests/product/product-update-request.model";

@Injectable({
    providedIn: 'root'
})
class ProductService {

    private apiUrl = `${API_BASE_URL}/products`;

    constructor(private http: HttpClient) { }

    getProductDetailsByBranchId(
        branchId: number
    ): Observable<ApiResponse<ProductDetailResponse[]>> {
        return this.http.get<ApiResponse<ProductDetailResponse[]>>(
            `${this.apiUrl}/details/branch/${branchId}`
        );
    }

    createProduct(
        request: ProductCreationRequest
    ): Observable<ApiResponse<void>> {
        return this.http.post<ApiResponse<void>>(
            this.apiUrl, 
            request
        );
    }

    updateProduct(
        productId: number, 
        request: ProductUpdateRequest
    ): Observable<ApiResponse<void>> {
        return this.http.put<ApiResponse<void>>(
            `${this.apiUrl}/${productId}`, 
            request
        );
    }
}

export { ProductService };