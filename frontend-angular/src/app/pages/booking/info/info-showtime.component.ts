import { Component, EventEmitter, Input, Output } from "@angular/core";
import { ShowtimeDetailResponse } from "../../../core/models/responses/showtime/showtime-detail-response.model";
import dayjs from "dayjs";

@Component({
    standalone: true,
    selector: 'info-showtime',
    templateUrl: './info-showtime.component.html',
    styleUrls: [
        './info-showtime.component.scss'
    ]
})
export class InfoShowtimeComponent {
    @Input() showtime!: ShowtimeDetailResponse;
    @Input() pageNumber!: number;

    @Output() pageChange = new EventEmitter<number>()

    dayjs = dayjs;

    changePage(pageNum: number) {
        if (pageNum > 2 || pageNum < 1) {
            return;
        } 
        this.pageNumber = pageNum;
        this.pageChange.emit(pageNum);
    }


}