import { Component, Input, OnDestroy, OnInit } from "@angular/core";
import { LoaderService } from "../../../core/services/ui/loader.service";
import { Observable, Subscription } from "rxjs";
import { BaseComponent } from "../base/base.component";

@Component({
    selector: "loading-overlay",
    templateUrl: "./loading-overlay.component.html",
    styleUrls: [
        "./loading-overlay.component.scss"
    ]
})
export class LoadingOverlayComponent {

    @Input() active: boolean = true;

}