import { AgeRatingLabelResponse } from "./age-rating-label-response.model";

interface MoviePreviewResponse {
    id: number;
    title: string;
    posterURL: string;
    ageRating: AgeRatingLabelResponse;
}

export type { MoviePreviewResponse };