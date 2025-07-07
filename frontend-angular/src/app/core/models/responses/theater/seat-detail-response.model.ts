import { SeatStatusResponse } from "./seat-status-response.model";
import { SeatTypeResponse } from "./seat-type-response.model";

interface SeatDetailResponse {
    id: number;
    name: string;
    positionX: number;
    positionY: number;
    type: SeatTypeResponse;
    isLocked: boolean;
    status: SeatStatusResponse;
}

export type { SeatDetailResponse };