import { Component, HostListener, OnInit } from "@angular/core";
import { Router, RouterModule } from "@angular/router";
import { CommonModule } from "@angular/common";
import { BaseComponent } from "../base/base.component";
import { UserService } from "../../../core/services/user/user.service";
import { AuthenticationService } from "../../../core/services/authentication/authentication.service";
import { UserPreviewResponse } from "../../../core/models/responses/user/user-preview-response.model";
import { response } from "express";

@Component({
    standalone: true,
    selector: "app-header",
    templateUrl: "./header.component.html",
    styleUrls: [
        "./header.component.scss"
    ],
    imports: [
        RouterModule,
        CommonModule
    ]
})
export class HeaderComponent implements OnInit {
    isHeaderActive: boolean = false;
    isMenuActive: boolean = false;
    isItemOpened: boolean[] = [];
    isOverlayActive: boolean = false;

    user!: UserPreviewResponse;

    constructor(
        private userService: UserService,
        private authService: AuthenticationService,
        private router: Router
    ) { }

    ngOnInit(): void {
        if (this.authService.isLoggedIn) {
            this.userService.getMyInfoPreview().subscribe({
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
            })
        }
    }

    @HostListener('window:scroll', [])
    onScroll() {
        this.isHeaderActive = window.scrollY > 0;
    }

    @HostListener('window:resize', [])
    onResize() {
        if (window.innerWidth>950) {
            this.isMenuActive = false;
        }
    }

    toggleMenu() {
        this.isMenuActive = !this.isMenuActive;
        this.isOverlayActive = !this.isOverlayActive;
    }

    toggleItem(index: number): void {
        this.isItemOpened[index] = !this.isItemOpened[index];
    }

    closeMenu() {
        this.isOverlayActive = false;
        this.isMenuActive = false;
    }

    logout(): void {
        this.authService.logout().subscribe({
            next: (response) => {
                if (response.success) {
                    this.router.navigate(['/login']);
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