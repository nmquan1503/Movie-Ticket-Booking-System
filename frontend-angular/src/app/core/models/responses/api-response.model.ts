interface ApiResponse<T> {
    code: number;
    success: boolean;
    message: string;
    data?: T | null;
}

export type { ApiResponse };