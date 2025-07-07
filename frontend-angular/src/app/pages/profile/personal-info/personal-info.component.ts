import { Component, Input } from "@angular/core";
import { UserDetailResponse } from "../../../core/models/responses/user/user-detail-response.model";
import { CommonModule } from "@angular/common";
import dayjs from "dayjs";

@Component({
    standalone: true,
    selector: 'personal-info',
    templateUrl: './personal-info.component.html',
    styleUrls: [
        './personal-info.component.scss'
    ],
    imports: [
        CommonModule
    ]
})
export class PersonalInfoComponent {
    @Input() user!: UserDetailResponse;   

    dayjs = dayjs;

    get formattedBirthday(): string {
        const date = dayjs(this.user.birthday);
        return `${date.date()} tháng ${date.month() + 1}`;
    }

    get formattedLocation(): string {
        if (this.user.specificAddress) {
            return `${this.user.specificAddress}, ${this.user.ward.name}, ${this.user.ward.district.name}, ${this.user.ward.district.province.name}`;
        }
        return `${this.user.ward.name}, ${this.user.ward.district.name}, ${this.user.ward.district.province.name}`;
    }

}