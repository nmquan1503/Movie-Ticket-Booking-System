import { CommonModule } from "@angular/common";
import { Component, Input } from "@angular/core";

@Component({
    standalone: true,
    selector: 'load-button',
    templateUrl: './load-button.component.html',
    styleUrls: [
        './load-button.component.scss'
    ],
    imports: [
        CommonModule
    ]
})
export class LoadButtonComponent {
    @Input() isLoading: boolean = false;
    @Input() isLastPage: boolean = false;
    @Input() action!: () => void;
}