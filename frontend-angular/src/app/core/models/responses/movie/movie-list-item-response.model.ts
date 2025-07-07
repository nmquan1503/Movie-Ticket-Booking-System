import { Dayjs } from "dayjs";
import { CategoryResponse } from "./category-response.model";
import { AgeRatingLabelResponse } from "./age-rating-label-response.model";

interface MovieListItemResponse {
    id: number;
    title: string;
    posterURL: string;
    releasedDate: string | Dayjs;
    duration: number;
    averageRating: number;
    ratingCount: number;
    categories: CategoryResponse[];
    ageRating: AgeRatingLabelResponse;
}

export type { MovieListItemResponse };