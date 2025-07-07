import { Dayjs } from "dayjs";

interface MovieCreationRequest {
    title: string;
    posterURL: string;
    bannerURL: string;
    trailerURL: string;
    overview: string;
    releasedDate: string | Dayjs;
    duration: number;
    originalLanguageId: number;
    subtitleLanguageId: number;
    ageRatingId: number;
    actorIds: number[];
    directorIds: number[];
    categoryIds: number[];
}

export type { MovieCreationRequest };