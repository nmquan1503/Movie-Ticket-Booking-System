import { Dayjs } from 'dayjs'
import { LanguageResponse } from './language-response.model'
import { AgeRatingDetailResponse } from './age-rating-detail-response.model'
import { PersonPreviewResponse } from './person-preview-response.model';
import { CategoryResponse } from './category-response.model';
import { Page } from '../../common/page.model';
import { MovieCastResponse } from './movie-cast-response.model';
import { MovieCrewResponse } from './movie-crew-response.model';
import { MovieImageResponse } from './movie-image-response.model';

interface MovieDetailResponse {
    id: number;
    title: string;
    posterURL: string | null;
    backdropURL: string | null;
    trailerURL: string | null;
    tagline: string | null;
    overview: string | null;
    releasedDate: string | Dayjs;
    duration: number;
    originalLanguage: LanguageResponse;
    ageRating: AgeRatingDetailResponse | null;
    averageRating: number;
    ratingCount: number;
    cast: Page<MovieCastResponse>;
    crew: Page<MovieCrewResponse>;
    categories: CategoryResponse[];
    images: Page<MovieImageResponse>;
}

export type { MovieDetailResponse };