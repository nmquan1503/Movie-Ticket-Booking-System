import { ProductCheckoutResponse } from "./product-checkout-response.model";

interface ProductReservationItemResponse {
    product: ProductCheckoutResponse;
    quantity: number;
}

export type { ProductReservationItemResponse };