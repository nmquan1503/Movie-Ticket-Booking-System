import { Directive, ElementRef, EventEmitter, HostListener, Output } from "@angular/core";

@Directive({
    selector: '[appClickOutside]'
})
class ClickOutsideDirective {
    @Output() appClickOutside = new EventEmitter<void>();

    constructor(private _elementRef: ElementRef) { }

    @HostListener('document:click', ['$event.target'])
    public onClick(targetElement: HTMLElement): void {
        const clickedInside = this._elementRef.nativeElement.contains(targetElement);
        if (!clickedInside) {
            this.appClickOutside.emit();
        }
    }
}

export { ClickOutsideDirective };