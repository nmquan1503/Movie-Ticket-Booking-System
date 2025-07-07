import { ShowtimeDetailResponse } from "../showtime/showtime-detail-response.model";
import { SeatDetailResponse } from "../theater/seat-detail-response.model";
import { ProductTicketItemResponse } from "../product/product-ticket-item-response.model";
import { TicketStatusResponse } from "./ticket-status-response.model";

interface TicketDetailResponse {
    id: number;
    showtime: ShowtimeDetailResponse;
    seats: SeatDetailResponse[];
    items: ProductTicketItemResponse[];
    status: TicketStatusResponse;
}

export type { TicketDetailResponse };