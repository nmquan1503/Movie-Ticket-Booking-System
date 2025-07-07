import { PersonPreviewResponse } from "./person-preview-response.model";

export interface MovieCastResponse {
    id: number;
    person: PersonPreviewResponse;
    character: string;
    order: number;
}