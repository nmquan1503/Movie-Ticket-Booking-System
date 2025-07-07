import { ProductOrderRequest } from "../product/product-order-request.model";

interface ReservationCreationRequest {
    showtimeId: number;
    seatIds: number[];
    productOrders: ProductOrderRequest[];
}

export type { ReservationCreationRequest };