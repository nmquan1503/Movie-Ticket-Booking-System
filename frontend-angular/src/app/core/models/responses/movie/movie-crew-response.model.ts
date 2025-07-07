import { PersonPreviewResponse } from "./person-preview-response.model";
import { PositionResponse } from "./position-response.model";

export interface MovieCrewResponse {
    id: number;
    person: PersonPreviewResponse;
    position: PositionResponse;
}