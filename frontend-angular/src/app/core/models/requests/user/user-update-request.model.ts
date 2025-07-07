interface UserUpdateRequest {
    fullName: string;
    avatarURL: string;
    password: string;
    genderId: number;
    wardId: number;
    specificAddress: string;
}

export type { UserUpdateRequest };