import { Injectable } from "@angular/core";
import { API_BASE_URL } from "../../constants/api.constants";
import { HttpClient } from "@angular/common/http";
import { PageRequest } from "../../models/requests/page-request.model";
import { Observable } from "rxjs";
import { ApiResponse } from "../../models/responses/api-response.model";
import { Page } from "../../models/common/page.model";
import { buildPageParams } from "../../utils/pagnination.util";
import { MovieImageResponse } from "../../models/responses/movie/movie-image-response.model";

@Injectable({
    providedIn: 'root'
})
export class MovieImageService {
    private apiUrl = `${API_BASE_URL}/movie-images`;

    constructor(private http: HttpClient) { }

    getMovieImagesByMovieId(
        movieId: number,
        pageable: PageRequest
    ): Observable<ApiResponse<Page<MovieImageResponse>>> {
        const params = buildPageParams(pageable);
        return this.http.get<ApiResponse<Page<MovieImageResponse>>>(
            `${this.apiUrl}/movie/${movieId}`,
            { params }
        );
    } 


}