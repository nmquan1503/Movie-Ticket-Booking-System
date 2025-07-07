import { Component, OnInit, OnDestroy, NgZone, AfterViewChecked, AfterViewInit } from "@angular/core";
import { RouterModule } from "@angular/router";
import { HomeBannerComponent } from "./banner/banner.component";
import { SearchBar } from "../../shared/components/search-bar/search-bar.component";
import { MovieSlideshowComponent } from "./movie-slideshow/movie-slideshow.component";
import { PromoSliderComponent } from "./promo-slider/promo-slider.component";
import { LoaderService } from "../../core/services/ui/loader.service";
import { BaseComponent } from "../../shared/components/base/base.component";

@Component({
    standalone: true,
    selector: "app-home-page",
    templateUrl: "./home-page.component.html",
    styleUrls: ["./home-page.component.scss"],
    imports: [
        RouterModule,
        HomeBannerComponent,
        SearchBar,
        MovieSlideshowComponent,
        PromoSliderComponent
    ],
})
export class HomePageComponent {

}
