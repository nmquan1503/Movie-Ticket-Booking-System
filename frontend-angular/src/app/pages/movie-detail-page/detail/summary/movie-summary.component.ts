import { Component, Input, OnInit } from "@angular/core";
import { MovieDetailResponse } from "../../../../core/models/responses/movie/movie-detail-response.model";
import { CommonModule } from "@angular/common";
import { SlideItemComponent } from "../../../../shared/components/slideshow/slide-item/slide-item.component";
import { SlideShowComponent } from "../../../../shared/components/slideshow/slideshow.component";
import { MovieCastResponse } from "../../../../core/models/responses/movie/movie-cast-response.model";
import { MovieCrewResponse } from "../../../../core/models/responses/movie/movie-crew-response.model";
import { PageRequest } from "../../../../core/models/requests/page-request.model";
import { MovieCastService } from "../../../../core/services/movie/movie-cast.service";
import { MovieCrewService } from "../../../../core/services/movie/movie-crew.service";
import { finalize } from "rxjs";
import { LoadButtonComponent } from "../../../../shared/components/load-button/load-button.component";

@Component({
    standalone: true,
    selector: 'movie-summary',
    templateUrl: './movie-summary.component.html',
    styleUrls: [
        './movie-summary.component.scss'
    ],
    imports: [
        CommonModule,
        LoadButtonComponent
    ]
})
export class MovieSummaryComponent implements OnInit{
    @Input() isVisible!: boolean;
    @Input() movie!: MovieDetailResponse;

    cast: MovieCastResponse[] = [];
    castPageable: PageRequest = {
        page: 0,
        size: 10
    }
    isLoadingCast: boolean = false;
    isLastPageCast: boolean = false;
    
    crew: MovieCrewResponse[] = [];
    crewPageable: PageRequest = {
        page: 0,
        size: 10
    }
    isLoadingCrew: boolean = false;
    isLastPageCrew: boolean = false;

    constructor(
        private movieCastService: MovieCastService,
        private movieCrewService: MovieCrewService
    ) { }

    ngOnInit(): void {
        if (this.movie) {
            this.cast = this.movie.cast.content;
            this.castPageable.page = this.movie.cast.pageable.pageNumber + 1;
            this.castPageable.size = this.movie.cast.pageable.pageSize;
            this.isLastPageCast = this.movie.cast.last;

            this.crew = this.movie.crew.content;
            this.crewPageable.page = this.movie.crew.pageable.pageNumber + 1;
            this.crewPageable.size = this.movie.crew.pageable.pageSize;
            this.isLastPageCrew = this.movie.crew.last;
        }
    }

    loadCast(): void {
        if (!this.movie || this.isLoadingCast || this.isLastPageCast) {
            return;
        }
        this.isLoadingCast = true;
        this.movieCastService.getMovieCastByMovieId(this.movie.id, this.castPageable).pipe(
            finalize(() => {
                this.isLoadingCast = false;
            })
        ).subscribe({
            next: (response) => {
                if (response.success && response.data) {
                    const page = response.data;
                    this.cast.push(...page.content);
                    this.castPageable.page += 1;
                    this.isLastPageCast = page.last;
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

    loadCrew(): void {
        if (!this.movie || this.isLoadingCrew || this.isLastPageCrew) {
            return;
        }
        this.isLoadingCrew = true;
        this.movieCrewService.getMovieCrewByMovieId(this.movie.id, this.crewPageable).pipe(
            finalize(() => {
                this.isLoadingCrew = false;
            })
        ).subscribe({
            next: (response) => {
                if (response.success && response.data) {
                    const page = response.data;
                    this.crew.push(...page.content);
                    this.crewPageable.page += 1;
                    this.isLastPageCrew = page.last;
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

}