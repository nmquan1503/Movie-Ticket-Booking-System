import { BranchOptionResponse } from "./branch-option-response.model";
import { RoomStatusResponse } from "./room-status-response.model";
import { RoomTypeSimpleResponse } from "./room-type-simple-response.model";

interface RoomDetailResponse {
    id: number;
    name: string;
    rowCount: number;
    columnCount: number;
    branch: BranchOptionResponse;
    type: RoomTypeSimpleResponse;
    status: RoomStatusResponse;
}

export type { RoomDetailResponse };