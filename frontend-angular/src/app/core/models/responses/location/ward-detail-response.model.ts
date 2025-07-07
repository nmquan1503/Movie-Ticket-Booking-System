import { DistrictDetailResponse } from "./district-detail-response.model";

interface WardDetailResponse {
    id: number;
    name: string;
    district: DistrictDetailResponse;
}

export type { WardDetailResponse };