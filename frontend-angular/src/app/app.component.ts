import { Component, OnInit } from "@angular/core";
import { NavigationCancel, NavigationEnd, NavigationError, NavigationStart, Router, RouterOutlet } from "@angular/router";
import { LoadingOverlayComponent } from "./shared/components/loading-overlay/loading-overlay.component";
import { LoaderService } from "./core/services/ui/loader.service";
import { BaseComponent } from "./shared/components/base/base.component";
import { AuthenticationService } from "./core/services/authentication/authentication.service";
import { WindowService } from "./core/services/platform-browser/window.service";
import { CommonModule } from "@angular/common";

@Component({
    selector: "app-root",
    templateUrl: "./app.component.html",
    styleUrls: [
        "./app.component.scss"
    ],
    imports: [
        RouterOutlet,
        CommonModule
    ]
})
export class AppComponent implements OnInit {
    title = 'front-end'

    constructor(
        private authService: AuthenticationService
    ) { }

    ngOnInit(): void {

    }
}