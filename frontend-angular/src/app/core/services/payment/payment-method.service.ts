import { Injectable } from "@angular/core";
import { API_BASE_URL } from "../../constants/api.constants";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { ApiResponse } from "../../models/responses/api-response.model";
import { PaymentMethodDetailResponse } from "../../models/responses/payment/payment-method-detail-response.model";

@Injectable({
    providedIn: 'root'
})
class PaymentMethodService {

    private apiUrl = `${API_BASE_URL}/payment-methods`;

    constructor(private http: HttpClient) { }

    getAllPaymentMethodDetails(): Observable<ApiResponse<PaymentMethodDetailResponse[]>> {
        return this.http.get<ApiResponse<PaymentMethodDetailResponse[]>>(
            this.apiUrl
        );
    }
}

export { PaymentMethodService };