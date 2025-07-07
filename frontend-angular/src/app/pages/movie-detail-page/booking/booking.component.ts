import { Component, Input } from "@angular/core";
import { CommonModule } from "@angular/common";
import { MovieDetailResponse } from "../../../core/models/responses/movie/movie-detail-response.model";
import { RouterModule } from "@angular/router";

@Component({
    standalone: true,
    selector: 'booking',
    templateUrl: './booking.component.html',
    styleUrls: [
        './booking.component.scss'
    ],
    imports: [
        CommonModule,
        RouterModule
    ]
})
export class BookingComponent {
    @Input() movie!: MovieDetailResponse;

    get starStates(): ('full' | 'half' | 'empty')[] {
        if (!this.movie) {
            return [];
        }
        const rating = this.movie.averageRating;
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

}