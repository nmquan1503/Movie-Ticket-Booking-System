import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { ActivatedRoute, Router, RouterModule } from "@angular/router";
import { AuthenticationService } from "../../core/services/authentication/authentication.service";
import { LoadingOverlayComponent } from "../../shared/components/loading-overlay/loading-overlay.component";

@Component({
    standalone: true,
    selector: 'login-page',
    templateUrl: './login-page.component.html',
    styleUrls: [
        './login-page.component.scss'
    ],
    imports: [
        FormsModule,
        RouterModule,
        CommonModule,
        LoadingOverlayComponent
    ]
})
export class LoginPageComponent {
    identifier: string = '';
    password: string = '';
    isRemembered: boolean = true;

    loading: boolean = false;

    notifications = [
        'Vui lòng nhập Email hoặc số điện thoại',
        'Vui lòng nhập mật khẩu',
        'Thông tin đăng nhập không đúng'
    ]

    notificationId: number = 0;

    constructor(
        private authService: AuthenticationService,
        private router: Router,
        private route: ActivatedRoute
    ) { }

    login(): void {
        console.log(`iden: ${this.identifier}`)
        console.log(`pass: ${this.password}`)
        if (!this.identifier) {
            this.notificationId = 1;
            return;
        }
        if (!this.password) {
            this.notificationId = 2;
            return;
        }
        this.loading = true;
        this.authService.authenticate({
            identifier: this.identifier,
            password: this.password
        }).subscribe({
            next: (response) => {
                if (response.success && response.data) {
                    const redirectTo = this.route.snapshot.queryParamMap.get('redirecTo') || '/home';
                    this.router.navigateByUrl(redirectTo);
                }
                else {
                    this.notificationId = 3;
                    console.log(`api error: ${response.message}`);
                }
                this.loading = false;
            },
            error: (err) => {
                console.log(`api error: ${err.error.message}`);
                this.notificationId = 3;
                this.loading = false;
            }
        });
    }
}