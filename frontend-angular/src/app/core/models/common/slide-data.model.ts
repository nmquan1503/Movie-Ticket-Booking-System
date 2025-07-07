import { TemplateRef } from "@angular/core";

interface SlideData {
    href?: string;
    routerLink?: string;
    template: TemplateRef<any>;
}

export type { SlideData };