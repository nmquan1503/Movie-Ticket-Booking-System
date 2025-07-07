import { AfterViewInit, Directive, OnInit } from "@angular/core";
import { LoaderService } from "../../../core/services/ui/loader.service";

@Directive()
export abstract class BaseComponent implements OnInit, AfterViewInit {

    constructor(
        protected loaderService: LoaderService
    ) { }

    ngOnInit(): void {
        this.loaderService.show();
    }

    ngAfterViewInit(): void {
        this.loaderService.hide();
    }

}