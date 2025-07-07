import { Dayjs } from "dayjs";
import { ShowtimeStatusResponse } from "./showtime-status-response.model";
import { RoomDetailResponse } from "../theater/room-detail-response.model";

interface ShowtimeOptionResponse {
    id: number;
    startTime: string | Dayjs;
    room: RoomDetailResponse;
    status: ShowtimeStatusResponse;
}

export type { ShowtimeOptionResponse };