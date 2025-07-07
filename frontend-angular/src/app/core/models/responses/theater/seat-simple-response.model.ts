import { SeatTypeResponse } from "./seat-type-response.model";

interface SeatSimpleResponse {
    id: number;
    name: string;
    type: SeatTypeResponse;
}

export type { SeatSimpleResponse };