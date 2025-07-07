import { CommonModule } from "@angular/common";
import { Component, Input } from "@angular/core";
import { ShowtimeOptionResponse } from "../../../core/models/responses/showtime/showtime-option-response.model";
import { RoomTypeSimpleResponse } from "../../../core/models/responses/theater/room-type-simple-response.model";
import dayjs from "dayjs";
import { RouterModule } from "@angular/router";

@Component({
    standalone: true,
    selector: 'showtime-list-plan',
    templateUrl: './showtime-list-plan.component.html',
    styleUrls: [
        './showtime-list-plan.component.scss'
    ],
    imports: [
        CommonModule,
        RouterModule
    ]
})
export class ShowtimeListPlanComponent {
    @Input() showtimeMap!: Map<number, Map<number, Map<string, Map<number, ShowtimeOptionResponse[]>>>>;
    @Input() types!: Map<number, RoomTypeSimpleResponse>;
    @Input() currentProvinceId!: number;
    @Input() currentBranchId!: number;
    @Input() currentDate!: string;

    dayjs = dayjs;

    get typeList(): RoomTypeSimpleResponse[] {
        if (!this.currentProvinceId || !this.currentBranchId || !this.currentDate) {
            return [];
        }
        const keys = this.showtimeMap.get(this.currentProvinceId)?.get(this.currentBranchId)?.get(this.currentDate)?.keys();
        if (!keys) {
            return [];
        }
        let typeList = [];
        for (let id of Array.from(keys)) {
            const type = this.types.get(id);
            if (type) {
                typeList.push(type);
            }
        }
        return typeList;
    }

    getShowtimeList(typeId: number): ShowtimeOptionResponse[] {
        let showtimeList = this.showtimeMap.get(this.currentProvinceId)?.get(this.currentBranchId)?.get(this.currentDate)?.get(typeId);
        return showtimeList ? showtimeList : [];
    }

}