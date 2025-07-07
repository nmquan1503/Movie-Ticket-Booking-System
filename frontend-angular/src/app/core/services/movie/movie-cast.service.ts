import { Injectable } from "@angular/core";
import { API_BASE_URL } from "../../constants/api.constants";
import { HttpClient } from "@angular/common/http";
import { PageRequest } from "../../models/requests/page-request.model";
import { Observable } from "rxjs";
import { ApiResponse } from "../../models/responses/api-response.model";
import { Page } from "../../models/common/page.model";
import { MovieCastResponse } from "../../models/responses/movie/movie-cast-response.model";
import { buildPageParams } from "../../utils/pagnination.util";

@Injectable({
    providedIn: 'root'
})
export class MovieCastService {
    private apiUrl = `${API_BASE_URL}/movie-cast`;

    constructor(private http: HttpClient) { }

    getMovieCastByMovieId(
        movieId: number,
        pageable: PageRequest
    ): Observable<ApiResponse<Page<MovieCastResponse>>> {
        const params = buildPageParams(pageable);
        return this.http.get<ApiResponse<Page<MovieCastResponse>>>(
            `${this.apiUrl}/movie/${movieId}`,
            { params }
        );
    }

}