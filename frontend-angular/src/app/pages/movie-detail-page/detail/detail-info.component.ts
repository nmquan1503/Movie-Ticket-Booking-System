import { CommonModule } from "@angular/common";
import { Component, Input } from "@angular/core";
import { MovieDetailResponse } from "../../../core/models/responses/movie/movie-detail-response.model";
import { MovieSummaryComponent } from "./summary/movie-summary.component";
import { MovieReviewComponent } from "./review/movie-review.component";

@Component({
    standalone: true,
    selector: 'movie-detail-info',
    templateUrl: './detail-info.component.html',
    styleUrls: [
        './detail-info.component.scss'
    ],
    imports: [
        CommonModule,
        MovieSummaryComponent,
        MovieReviewComponent
    ]
})
export class MovieDetailInfoComponent {
    @Input() movie!: MovieDetailResponse;

    tab: 'summary' | 'review' = 'summary';

    changeTab(newTab: 'summary' | 'review'): void {
        if (newTab !== this.tab) {
            this.tab = newTab;
        }
    }

}