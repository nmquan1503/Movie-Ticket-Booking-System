import { BranchSimpleResponse } from "./branch-simple-response.model";
import { RoomTypeFeatureResponse } from "./room-type-feature-response.model";

export interface RoomTypeDetailResponse {
    id: number;
    name: string;
    iconURL: string;
    overview: string;
    branches: BranchSimpleResponse[];
    features: RoomTypeFeatureResponse[];
}