import { Injectable } from "@angular/core";
import { API_BASE_URL } from "../../constants/api.constants";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Pageable } from "../../models/common/pageable.model";
import { Observable } from "rxjs";
import { ApiResponse } from "../../models/responses/api-response.model";
import { Page } from "../../models/common/page.model";
import { MovieReviewResponse } from "../../models/responses/movie/movie-review-response.model";
import { MovieReviewCreationRequest } from "../../models/requests/movie/movie-review-creation-request.model";
import { MovieReviewUpdateRequest } from "../../models/requests/movie/movie-review-update-request.model";
import { PageRequest } from "../../models/requests/page-request.model";
import { buildPageParams } from "../../utils/pagnination.util";

@Injectable({
    providedIn: 'root'
})
class MovieReviewService {
    
    private apiUrl = `${API_BASE_URL}/movie-reviews`

    constructor(private http: HttpClient) { }

    getMovieReviewPage(
        movieId: number, 
        pageable: PageRequest
    ): Observable<ApiResponse<Page<MovieReviewResponse>>> {
        const params = buildPageParams(pageable);
        return this.http.get<ApiResponse<Page<MovieReviewResponse>>>(
            `${this.apiUrl}/movie/${movieId}`,
            { params }
        );
    }

    createReview(
        request: MovieReviewCreationRequest
    ): Observable<ApiResponse<MovieReviewResponse>> {
        return this.http.post<ApiResponse<MovieReviewResponse>>(
            this.apiUrl,
            request
        );
    }

    updateReview(
        movieReviewId: number, 
        request: MovieReviewUpdateRequest
    ): Observable<ApiResponse<void>> {
        return this.http.put<ApiResponse<void>>(
            `${this.apiUrl}/${movieReviewId}`,
            request
        )
    }

    deleteReview(
        movieReviewId: number
    ): Observable<ApiResponse<void>> {
        return this.http.delete<ApiResponse<void>>(
            `${this.apiUrl}/${movieReviewId}`
        )
    }


}

export { MovieReviewService };