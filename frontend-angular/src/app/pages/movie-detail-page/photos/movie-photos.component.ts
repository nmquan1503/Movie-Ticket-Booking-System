import { CommonModule } from "@angular/common";
import { Component, Input, OnInit, ViewEncapsulation } from "@angular/core";
import { MovieDetailResponse } from "../../../core/models/responses/movie/movie-detail-response.model";
import { MovieImageResponse } from "../../../core/models/responses/movie/movie-image-response.model";
import { PageRequest } from "../../../core/models/requests/page-request.model";
import { MovieImageService } from "../../../core/services/movie/movie-image.service";
import { finalize } from "rxjs";
import { SlideShowComponent } from "../../../shared/components/slideshow/slideshow.component";
import { SlideItemComponent } from "../../../shared/components/slideshow/slide-item/slide-item.component";
import { LoadButtonComponent } from "../../../shared/components/load-button/load-button.component";

@Component({
    standalone: true,
    selector: 'movie-photos',
    templateUrl: './movie-photos.component.html',
    styleUrls: [
        './movie-photos.component.scss'
    ],
    imports: [
        CommonModule,
        SlideShowComponent,
        SlideItemComponent,
        LoadButtonComponent
    ],
    encapsulation: ViewEncapsulation.None
})
export class MoviePhotosComponent implements OnInit {
    @Input() movie!: MovieDetailResponse;

    images: MovieImageResponse[] = [];

    pageable: PageRequest = {
        page: 0,
        size: 5
    }

    isLoading: boolean = false;
    isLastPage: boolean = false;

    constructor(
        private movieImageService: MovieImageService
    ) { }

    ngOnInit(): void {
        if (this.movie) {
            this.images = this.movie.images.content;
            this.pageable.page = this.movie.images.pageable.pageNumber + 1;
            this.pageable.size = this.movie.images.pageable.pageSize;
            this.isLastPage = this.movie.images.last;
        }
    }

    loadImages(): void {
        if (!this.movie || this.isLoading || this.isLastPage) {
            return;
        }
        this.isLoading = true;
        this.movieImageService.getMovieImagesByMovieId(this.movie.id, this.pageable).pipe(
            finalize(() => {
                this.isLoading = false;
            })
        ).subscribe({
            next: (response) => {
                if (response.success && response.data) {
                    const page = response.data;
                    this.images.push(...page.content);
                    this.isLastPage = page.last;
                    this.pageable.page += 1;
                }
                else {
                    console.log(`api error: ${response.message}`)
                }
            },
            error: (err) => {
                console.log(`error: ${err}`)
            }
        });
    }

}