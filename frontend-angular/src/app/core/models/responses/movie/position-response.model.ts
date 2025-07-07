import { DepartmentResponse } from "./department-response.model";

export interface PositionResponse {
    id: number;
    department: DepartmentResponse;
    name: string;
}