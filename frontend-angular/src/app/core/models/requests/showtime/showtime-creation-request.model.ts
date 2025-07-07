import { Dayjs } from "dayjs";

interface ShowtimeCreationRequest {
    movieId: number;
    roomId: number;
    startTime: string | Dayjs;
}

export type { ShowtimeCreationRequest };