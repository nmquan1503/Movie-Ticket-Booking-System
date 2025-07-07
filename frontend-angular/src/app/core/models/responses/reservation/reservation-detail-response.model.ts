import { Dayjs } from "dayjs";
import { ProductReservationItemResponse } from "../product/product-reservation-item-response.model";
import { ReservationStatusResponse } from "./reservation-status-response.model";
import { SeatDetailResponse } from "../theater/seat-detail-response.model";
import { ShowtimeDetailResponse } from "../showtime/showtime-detail-response.model";

interface ReservationDetailResponse {
    id: number;
    showtime: ShowtimeDetailResponse;
    ticketPrice: number;
    startTime: string | Dayjs;
    endTime: string | Dayjs;
    seats: SeatDetailResponse[];
    items: ProductReservationItemResponse[];
    status: ReservationStatusResponse;
}

export type { ReservationDetailResponse };