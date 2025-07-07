import { ProvinceDetailResponse } from "../location/province-detail-response.model";
import { BranchStatusResponse } from "./branch-status-response.model";

interface BranchOptionResponse {
    id: number;
    name: string;
    province: ProvinceDetailResponse;
    status: BranchStatusResponse;
}

export type { BranchOptionResponse };