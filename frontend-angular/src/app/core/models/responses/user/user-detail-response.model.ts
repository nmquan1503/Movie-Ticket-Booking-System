import { Dayjs } from "dayjs";
import { GenderOptionResponse } from "./gender-option-response.model";
import { WardDetailResponse } from "../location/ward-detail-response.model";

interface UserDetailResponse {
    id: number;
    fullName: string;
    avatarURL: string;
    phone: string;
    email: string;
    birthday: string | Dayjs;
    gender: GenderOptionResponse;
    ward: WardDetailResponse;
    specificAddress: string;
}

export type { UserDetailResponse };