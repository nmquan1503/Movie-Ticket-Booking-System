import { AfterViewInit, ChangeDetectorRef, Component, ElementRef, HostListener, ViewChild } from "@angular/core";
import { SlideShowComponent } from "../../../shared/components/slideshow/slideshow.component";
import { SlideItemComponent } from "../../../shared/components/slideshow/slide-item/slide-item.component";
import { WindowService } from "../../../core/services/platform-browser/window.service";
import { BaseComponent } from "../../../shared/components/base/base.component";

@Component({
    standalone: true,
    selector: "home-promo-slider",
    templateUrl: "./promo-slider.component.html",
    styleUrls: [
        "./promo-slider.component.scss"
    ],
    imports: [
        SlideShowComponent,
        SlideItemComponent
    ]
})
export class PromoSliderComponent {
}