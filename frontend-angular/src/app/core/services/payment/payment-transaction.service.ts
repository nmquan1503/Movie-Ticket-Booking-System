import { Injectable } from "@angular/core";
import { API_BASE_URL } from "../../constants/api.constants";
import { HttpClient } from "@angular/common/http";
import { PaymentTransactionCreationRequest } from "../../models/requests/payment/payment-transaction-creation-request.model";
import { Observable } from "rxjs";
import { ApiResponse } from "../../models/responses/api-response.model";
import { PaymentRedirectResponse } from "../../models/responses/payment/payment-redirect-response.model";

@Injectable({
    providedIn: 'root'
})
class PaymentTransactionService {

    private apiUrl = `${API_BASE_URL}/payment-transactions`;

    constructor(private http: HttpClient) { }

    createPayment(
        request: PaymentTransactionCreationRequest
    ): Observable<ApiResponse<PaymentRedirectResponse>> {
        return this.http.post<ApiResponse<PaymentRedirectResponse>>(
            this.apiUrl, request
        );
    }

    handleReturn(
        params: any
    ): Observable<ApiResponse<void>> {
        return this.http.get<ApiResponse<void>>(
            `${this.apiUrl}/callback`, 
            { params }
        );
    }
}

export { PaymentTransactionService };