import { CommonModule } from "@angular/common";
import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { BranchOptionResponse } from "../../../core/models/responses/theater/branch-option-response.model";
import { ProvinceDetailResponse } from "../../../core/models/responses/location/province-detail-response.model";
import { ShowtimeOptionResponse } from "../../../core/models/responses/showtime/showtime-option-response.model";
import { ClickOutsideDirective } from "../../../shared/directives/click-outside.directive";

@Component({
    standalone: true,
    selector: 'showtime-list-filter',
    templateUrl: './showtime-list-filter.component.html',
    styleUrls: [
        './showtime-list-filter.component.scss'
    ],
    imports: [
        CommonModule,
        ClickOutsideDirective
    ]
})
export class ShowtimeListFilterComponent implements OnInit {
    @Input() currentProvinceId!: number;
    @Input() currentBranchId!: number;
    @Input() currentDate!: string;
    @Input() provinces!: Map<number, ProvinceDetailResponse>;
    @Input() branches!: Map<number, BranchOptionResponse>;
    @Input() showtimeMap!: Map<number, Map<number, Map<string, Map<number, ShowtimeOptionResponse[]>>>>;

    @Output() provinceChange = new EventEmitter<number>();
    @Output() branchChange = new EventEmitter<number>();
    @Output() dateChange = new EventEmitter<string>();

    isProvinceListOpen: boolean = false;
    isBranchListOpen: boolean = false;
    isDateListOpen: boolean = false;

    ngOnInit(): void {
        console.log(this.currentProvinceId);
        console.log(this.provinces.get(this.currentProvinceId)?.name);
        console.log(this.currentBranchId)
        console.log(this.branches.get(this.currentBranchId));
    }

    changeProvince(provinceId: number) {
        this.provinceChange.emit(provinceId);
        this.currentProvinceId = provinceId;
        const branchId = this.showtimeMap.get(this.currentProvinceId)?.keys().next().value;
        if (branchId) {
            this.changeBranch(branchId);
        }
    }

    toggleProvinceList(): void {
        this.isProvinceListOpen = !this.isProvinceListOpen;
    }

    changeBranch(branchId: number) {
        this.branchChange.emit(branchId);
        this.currentBranchId = branchId;
        const date = this.showtimeMap.get(this.currentProvinceId)?.get(this.currentBranchId)?.keys().next().value;
        if (date) {
            this.changeDate(date);
        }
    }

    toggleBranchList(): void {
        this.isBranchListOpen = !this.isBranchListOpen;
    }

    changeDate(date: string) {
        this.dateChange.emit(date);
        this.currentDate = date;
    }

    toggleDateList(): void {
        this.isDateListOpen = !this.isDateListOpen;
    }

    get provinceList(): ProvinceDetailResponse[] {
        return Array.from(this.provinces.values());
    }

    get branchList(): BranchOptionResponse[] {
        const map = this.showtimeMap.get(this.currentProvinceId);
        if (!map) {
            return [];
        }
        const ids = Array.from(map.keys());
        let branchList = [];
        for (let id of ids) {
            const branch = this.branches.get(id);
            if (branch) {
                branchList.push(branch);
            }
        }
        return branchList;
    }

    get dateList(): string[] {
        const map = this.showtimeMap.get(this.currentProvinceId)?.get(this.currentBranchId);
        if (!map) {
            return [];
        }
        return Array.from(map.keys());
    }

}