import { Injectable } from "@angular/core";
import { API_BASE_URL } from "../../constants/api.constants";
import { HttpClient } from "@angular/common/http";
import { PageRequest } from "../../models/requests/page-request.model";
import { Observable } from "rxjs";
import { ApiResponse } from "../../models/responses/api-response.model";
import { Page } from "../../models/common/page.model";
import { MovieCastResponse } from "../../models/responses/movie/movie-cast-response.model";
import { buildPageParams } from "../../utils/pagnination.util";
import { MovieCrewResponse } from "../../models/responses/movie/movie-crew-response.model";

@Injectable({
    providedIn: 'root'
})
export class MovieCrewService {
    private apiUrl = `${API_BASE_URL}/movie-crew`;

    constructor(private http: HttpClient) { }

    getMovieCrewByMovieId(
        movieId: number,
        pageable: PageRequest
    ): Observable<ApiResponse<Page<MovieCrewResponse>>> {
        const params = buildPageParams(pageable);
        return this.http.get<ApiResponse<Page<MovieCrewResponse>>>(
            `${this.apiUrl}/movie/${movieId}`,
            { params }
        );
    } 


}