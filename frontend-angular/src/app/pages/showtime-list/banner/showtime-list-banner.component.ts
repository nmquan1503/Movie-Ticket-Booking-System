import { CommonModule } from "@angular/common";
import { Component, Input } from "@angular/core";
import { MovieBannerResponse } from "../../../core/models/responses/movie/movie-banner-response.model";

@Component({
    standalone: true,
    selector: 'showtime-list-banner',
    templateUrl: './showtime-list-banner.component.html',
    styleUrls: [
        './showtime-list-banner.component.scss'
    ],
    imports: [
        CommonModule
    ]
})
export class ShowtimeListBannerComponent {
    @Input() movie!: MovieBannerResponse;

    get backdropURL(): string {
        if (this.movie.backdropURL) {
            return  `url(${this.movie.backdropURL})`;
        }
        return "/assets/images/placeholders/default-backdrop.jpg";
    }

}