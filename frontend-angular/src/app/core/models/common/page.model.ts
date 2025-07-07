import { Pageable } from "./pageable.model";
import { Sort } from "./sort.model";

interface Page<T> {
    content: T[];
    pageable: Pageable;
    totalPages: number; 
    totalElements: number;
    last: boolean;
    first: boolean;
    sort: Sort;
    numberOfElements: number;
    size: number;
    number: number;
    empty: boolean;
}

export type { Page };