import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { MovieService } from "../../core/services/movie/movie.service";
import { ShowtimeService } from "../../core/services/showtime/showtime.service";
import { MovieBannerResponse } from "../../core/models/responses/movie/movie-banner-response.model";
import { ShowtimeListBannerComponent } from "./banner/showtime-list-banner.component";
import { ShowtimeListFilterComponent } from "./filter/showtime-list-filter.component";
import { ShowtimeListPlanComponent } from "./plan/showtime-list-plan.component";
import { ProvinceDetailResponse } from "../../core/models/responses/location/province-detail-response.model";
import { BranchOptionResponse } from "../../core/models/responses/theater/branch-option-response.model";
import { ShowtimeOptionResponse } from "../../core/models/responses/showtime/showtime-option-response.model";
import { RoomTypeSimpleResponse } from "../../core/models/responses/theater/room-type-simple-response.model";
import dayjs from "dayjs";

@Component({
    standalone: true,
    selector: 'showtime-list-page',
    templateUrl: './showtime-list-page.component.html',
    styleUrls: [
        './showtime-list-page.component.scss'
    ],
    imports: [
        CommonModule,
        ShowtimeListBannerComponent,
        ShowtimeListFilterComponent,
        ShowtimeListPlanComponent
    ]
})
export class ShowtimeListPageComponent implements OnInit {

    movieId!: number;
    movie!: MovieBannerResponse;
    
    provinces: Map<number, ProvinceDetailResponse> = new Map();
    branches: Map<number, BranchOptionResponse> = new Map();
    types: Map<number, RoomTypeSimpleResponse> = new Map();

    currentProvinceId!: number;
    currentBranchId!: number;
    currentDate!: string;

    showtimeMap: Map<number, Map<number, Map<string, Map<number, ShowtimeOptionResponse[]>>>> = new Map();

    constructor(
        private route: ActivatedRoute,
        private movieService: MovieService,
        private showtimeService: ShowtimeService
    ) { }

    ngOnInit(): void {
        const id = Number(this.route.snapshot.paramMap.get('id'));
        if (!isNaN(id)) {
            this.movieId = id;
            this.loadBanner();
            this.loadShowtimes();
        }
    }

    loadBanner(): void {
        this.movieService.getMovieBanner(this.movieId).subscribe({
            next: (response) => {
                if (response.success && response.data) {
                    this.movie = response.data;
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

    loadShowtimes(): void {
        this.showtimeService.getShowtimeOptionByMovieId(this.movieId).subscribe({
            next: (response) => {
                if (response.success && response.data) {
                    for (const showtime of response.data) {
                        const tempProvinceId = showtime.room.branch.province.id;
                        const tempBranchId = showtime.room.branch.id;
                        const tempDate = dayjs(showtime.startTime).format('YYYY-MM-DD');
                        const tempTypeId = showtime.room.type.id;
                        if (!this.showtimeMap.has(tempProvinceId)) {
                            this.showtimeMap.set(tempProvinceId, new Map());
                            this.provinces.set(tempProvinceId, showtime.room.branch.province);
                        }
                        if (!this.showtimeMap.get(tempProvinceId)?.has(tempBranchId)) {
                            this.showtimeMap.get(tempProvinceId)?.set(tempBranchId, new Map());
                            this.branches.set(tempBranchId, showtime.room.branch);
                        }
                        if (!this.showtimeMap.get(tempProvinceId)?.get(tempBranchId)?.has(tempDate)) {
                            this.showtimeMap.get(tempProvinceId)?.get(tempBranchId)?.set(tempDate, new Map());
                        }
                        if (!this.showtimeMap.get(tempProvinceId)?.get(tempBranchId)?.get(tempDate)?.has(tempTypeId)) {
                            this.showtimeMap.get(tempProvinceId)?.get(tempBranchId)?.get(tempDate)?.set(tempTypeId, []);
                            this.types.set(showtime.room.type.id, showtime.room.type);
                        }
                        this.showtimeMap.get(tempProvinceId)?.get(tempBranchId)?.get(tempDate)?.get(tempTypeId)?.push(showtime);
                    }
                    if (this.showtimeMap.size !== 0) {
                        const provinceId = this.showtimeMap.keys().next().value;
                        if (provinceId) {
                            this.currentProvinceId = provinceId;
                            const branchId = this.showtimeMap.get(this.currentProvinceId)?.keys().next().value;
                            if (branchId) {
                                this.currentBranchId = branchId;
                                const date = this.showtimeMap.get(this.currentProvinceId)?.get(this.currentBranchId)?.keys().next().value;
                                if (date) {
                                    this.currentDate = date;
                                }
                            }
                        }
                    }
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

    changeProvince(provinceId: number) {
        this.currentProvinceId = provinceId;
    }

    changeBranch(branchId: number) {
        this.currentBranchId = branchId;
    }

    changeDate(date: string) {
        this.currentDate = date;
    }

}