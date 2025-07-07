import { Component } from "@angular/core";
import { RouterModule } from "@angular/router";
import { HeaderComponent } from "../../shared/components/header/header.component";
import { FooterComponent } from "../../shared/components/footer/footer.component";

@Component({
    standalone: true,
    selector: "app-main-layout",
    templateUrl: "./main-layout.component.html",
    styleUrls: [
        "./main-layout.component.scss"
    ],
    imports: [
        RouterModule,
        HeaderComponent,
        FooterComponent
    ]
})

export class MainLayoutComponent { }