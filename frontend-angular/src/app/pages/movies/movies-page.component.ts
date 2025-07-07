import { Component } from "@angular/core";
import { BaseComponent } from "../../shared/components/base/base.component";
import { MovieBannerComponent } from "./banner/movie-banner.component";
import { SearchBar } from "../../shared/components/search-bar/search-bar.component";
import { MovieGridComponent } from "./movie-grid/movie-grid.component";

@Component({
    selector: "app-movies-page",
    templateUrl: "./movies-page.component.html",
    styleUrls: [
        "./movies-page.component.scss"
    ],
    imports: [
        MovieBannerComponent,
        SearchBar,
        MovieGridComponent
    ]
})
export class MoviesPageComponent {

}