import { PermissionResponse } from "./permission-response.model";

interface RoleResponse {
    id: number;
    name: string;
    permissions: PermissionResponse[];
}

export type { RoleResponse };