import { ProductSimpleResponse } from "./product-simple-response.model";

interface ProductTicketItemResponse {
    product: ProductSimpleResponse;
    quantity: number;
}

export type { ProductTicketItemResponse };