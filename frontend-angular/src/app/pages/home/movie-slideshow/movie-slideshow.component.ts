import { ChangeDetectorRef, Component, OnInit, ViewEncapsulation } from "@angular/core";
import { MovieService } from "../../../core/services/movie/movie.service";
import { CommonModule } from "@angular/common";
import { SlideShowComponent } from "../../../shared/components/slideshow/slideshow.component";
import { SlideItemComponent } from "../../../shared/components/slideshow/slide-item/slide-item.component";
import { MoviePreviewResponse } from "../../../core/models/responses/movie/movie-preview-response.model";
import { BaseComponent } from "../../../shared/components/base/base.component";
import { LoaderService } from "../../../core/services/ui/loader.service";

@Component({
    standalone: true,
    selector: "movie-slideshow",
    templateUrl: "./movie-slideshow.component.html",
    styleUrls: [
        "./movie-slideshow.component.scss"
    ],
    imports: [
        CommonModule,
        SlideShowComponent,
        SlideItemComponent
    ],
    encapsulation: ViewEncapsulation.None
})
class MovieSlideshowComponent implements OnInit {

    movies: MoviePreviewResponse[] = [];

    constructor(
        private movieService: MovieService,
        private cdr: ChangeDetectorRef
    ) { }

    ngOnInit(): void {
        this.loadMovies();
    }

    private loadMovies(): void {
        console.log(this.movies);
        this.movieService.getTrendingMoviePreviews().subscribe({
            next: (response) => {
                if (response.success === true) {
                    this.movies = response.data ?? [];
                }
                else {
                    console.log(`code: ${response.code}\nmessage: ${response.message}`);
                }
            },
            error: (err) => {
                console.log(`error: ${err}`);
            }

        });
    }

    getLinkBackgroundAgeRating(movie: MoviePreviewResponse): string {
        return `/assets/images/ratings/${movie.ageRating.code}.png`;
    }

}

export { MovieSlideshowComponent };