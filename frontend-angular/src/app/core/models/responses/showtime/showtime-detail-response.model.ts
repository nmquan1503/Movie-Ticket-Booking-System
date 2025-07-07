import { Dayjs } from "dayjs";
import { MoviePreviewResponse } from "../movie/movie-preview-response.model";
import { RoomDetailResponse } from "../theater/room-detail-response.model";
import { MovieBannerComponent } from "../../../../pages/movies/banner/movie-banner.component";
import { MovieBannerResponse } from "../movie/movie-banner-response.model";

interface ShowtimeDetailResponse {
    id: number;
    movie: MovieBannerResponse;
    room: RoomDetailResponse;
    startTime: string | Dayjs;
}

export type { ShowtimeDetailResponse };