import { Dayjs } from "dayjs";

export interface TicketPriceResponse {
    id: number;
    dayOfWeek: number;
    timeRangeStart: string | Dayjs;
    timeRangeEnd: string | Dayjs;
    price: number;
}