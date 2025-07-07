import { Directive, HostListener } from "@angular/core";

@Directive({
    selector: '[preventClickOnDrag]'
})
export class PreventClickOnDragDirective {

    private dragging = false;
    private startX = 0;
    private startY = 0;

    @HostListener('mousedown', ['$event'])
    onMouseDown(event: MouseEvent) {
        this.startX = event.clientX;
        this.startY = event.clientY;
        this.dragging = false;
    }

    @HostListener('mousemove', ['$event'])
    onMouseMove(event: MouseEvent) {
        const dx = Math.abs(event.clientX - this.startX);
        const dy = Math.abs(event.clientY - this.startY);
        if (dx > 2 || dy > 2) {
            this.dragging = true;
        }
    }

    @HostListener('click', ['$event'])
    onClick(event: MouseEvent) {
        if (this.dragging) {
            event.preventDefault();
            event.stopImmediatePropagation();
            this.dragging = false;
        }
    }

}