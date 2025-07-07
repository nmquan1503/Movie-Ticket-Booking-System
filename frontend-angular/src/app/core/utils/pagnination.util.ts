import { HttpParams } from "@angular/common/http";
import { PageRequest } from "../models/requests/page-request.model";

export function buildPageParams(pageable: PageRequest): HttpParams {
    let params = new HttpParams()
        .set('page', pageable.page.toString())
        .set('size', pageable.size.toString());
    
    if (pageable.sort) {
        if (Array.isArray(pageable.sort)) {
            pageable.sort.forEach(sort => {
                params = params.append('sort', sort);
            });
        }
        else {
            params = params.set('sort', pageable.sort);
        }
    }
    
    return params;
}