import { PaymentMethodStatusResponse } from "./payment-method-status-response.model";

interface PaymentMethodDetailResponse {
    id: number;
    name: string;
    thumbnailURL: string;
    status: PaymentMethodStatusResponse;
}

export type { PaymentMethodDetailResponse };