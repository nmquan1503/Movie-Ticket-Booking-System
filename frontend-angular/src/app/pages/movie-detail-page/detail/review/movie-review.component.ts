import { Component, Input, OnInit } from "@angular/core";
import { MovieDetailResponse } from "../../../../core/models/responses/movie/movie-detail-response.model";
import { CommonModule } from "@angular/common";
import { MovieReviewResponse } from "../../../../core/models/responses/movie/movie-review-response.model";
import { PageRequest } from "../../../../core/models/requests/page-request.model";
import { MovieReviewService } from "../../../../core/services/movie/movie-review.service";
import { finalize } from "rxjs";
import dayjs, { Dayjs, PluginFunc } from "dayjs";
import relativeTime from 'dayjs/plugin/relativeTime';
import 'dayjs/locale/vi';

dayjs.extend(relativeTime as PluginFunc);
dayjs.locale('vi');

@Component({
    standalone: true,
    selector: 'movie-review',
    templateUrl: './movie-review.component.html',
    styleUrls: [
        './movie-review.component.scss'
    ],
    imports: [
        CommonModule
    ]
})
export class MovieReviewComponent implements OnInit {
    @Input() isVisible!: boolean;
    @Input() movie!: MovieDetailResponse; 

    reviews: MovieReviewResponse[] = [];

    pageable: PageRequest = {
        page: 0,
        size: 10
    }

    isLoading: boolean = false;
    isLastPage: boolean = false;

    constructor(
        private movieReviewService: MovieReviewService
    ) { }

    ngOnInit(): void {
        this.loadReviews();
    }

    loadReviews(): void {
        if (!this.movie || this.isLoading || this.isLastPage) {
            return;
        }
        this.isLoading = true;
        this.movieReviewService.getMovieReviewPage(this.movie.id, this.pageable).pipe(
            finalize(() => {
                this.isLoading = false;
            })
        ).subscribe({
            next: (response) => {
                if (response.success && response.data) {
                    const page = response.data;
                    this.reviews.push(...page.content);
                    this.isLastPage = page.last;
                    this.pageable.page += 1;
                }
                else {
                    console.log(`api error: ${response.message}`);
                }
            },
            error: (err) => {
                console.log(`error: ${err}`);
            }
        });
    }

    starStates(rating: number): ('full' | 'half' | 'empty')[] {
        const states :('full' | 'half' | 'empty')[] = [];
        for (let i = 1; i <= 5; i++) {
            if (rating >= i) {
                states.push('full');
            }
            else if (rating >= i - 0.5) {
                states.push('half');
            }
            else {
                states.push('empty');
            }
        }
        return states;
    }

    getStarClass(state: 'full' | 'half' | 'empty'): string[] {
        if (state === 'full') {
            return ['fas', 'fa-star'];
        }
        else if (state === 'half') {
            return ['fas', 'fa-star-half-alt'];
        }
        else if (state === 'empty') {
            return ['far', 'fa-star'];
        }
        return [];
    }

    getTimeString(creationTime: string | Dayjs): string {
        const time = dayjs(creationTime);
        let str = time.fromNow();
        str = str.charAt(0).toUpperCase() + str.slice(1);
        return str;
    }

}