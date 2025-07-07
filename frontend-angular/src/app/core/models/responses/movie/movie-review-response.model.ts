import { Dayjs } from "dayjs";
import { UserPreviewResponse } from "../user/user-preview-response.model";

interface MovieReviewResponse {
    id: number;
    user: UserPreviewResponse;
    rating: number;
    comment: string;
    creationTime: string | Dayjs;
}

export type { MovieReviewResponse };