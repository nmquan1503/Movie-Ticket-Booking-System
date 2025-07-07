import { ProvinceDetailResponse } from "./province-detail-response.model"

interface DistrictDetailResponse {
    id: number;
    name: string;
    province: ProvinceDetailResponse
}

export type { DistrictDetailResponse };