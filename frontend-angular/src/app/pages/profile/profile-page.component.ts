import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { PersonalInfoComponent } from "./personal-info/personal-info.component";
import { UserService } from "../../core/services/user/user.service";
import { UserDetailResponse } from "../../core/models/responses/user/user-detail-response.model";

@Component({
    standalone: true,
    selector: 'profile-page',
    templateUrl: './profile-page.component.html',
    styleUrls: [
        './profile-page.component.scss'
    ],
    imports: [
        CommonModule,
        PersonalInfoComponent
    ]
})
export class ProfilePageComponent implements OnInit {

    page: number = 1;

    user!: UserDetailResponse;

    constructor(
        private userService: UserService
    ) { }

    ngOnInit(): void {
        this.userService.getMyInfo().subscribe({
            next: (response) => {
                if (response.success && response.data) {
                    this.user = response.data;
                }
                else {
                    console.log(`api error: ${response.message}`);
                }
            },
            error: (err) => {
                console.log(`error: ${err.error.message}`);
            }
        });
    }

}