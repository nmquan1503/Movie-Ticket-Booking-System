import { Dayjs } from "dayjs";

interface UserCreationRequest {
    fullName: string;
    phone: string;
    email: string;
    password: string;
    birthday: string | Dayjs;
    genderId: number;
    wardId: number;
    specificAddress: string | null;
}

export type { UserCreationRequest };