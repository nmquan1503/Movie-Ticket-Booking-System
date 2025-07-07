import { Component, Input, OnInit } from "@angular/core";
import { MovieDetailResponse } from "../../../core/models/responses/movie/movie-detail-response.model";
import { CommonModule } from "@angular/common";
import dayjs from "dayjs";

@Component({
    standalone: true,
    selector: 'metadata',
    templateUrl: './metadata.component.html',
    styleUrls: [
        './metadata.component.scss'
    ],
    imports: [
        CommonModule
    ]
})
export class MetadataComponent {
    @Input() movie!: MovieDetailResponse;

    dayjs = dayjs;

    get categoriesString(): string {
        return this.movie.categories.map(cate => cate.name).join(", ");
    }

    get posterURL(): string {
        if (this.movie.posterURL) {
            return this.movie.posterURL;
        }
        return "/assets/images/placeholders/movie-poster-template-03.jpg";
    }
}