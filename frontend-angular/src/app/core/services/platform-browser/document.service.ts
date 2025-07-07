import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
class DocumentService {
    
    get nativeDocument(): Document | null {
        return typeof document !== 'undefined' ? document : null;
    }

    addEventListener(
        type: string,
        listener: EventListenerOrEventListenerObject,
        options?: boolean | AddEventListenerOptions
    ): void {
        const doc = this.nativeDocument;
        if (doc) {
            doc.addEventListener(type, listener, options);
        }
        else {

        }
    }

    removeEventListener(
        type: string,
        listener: EventListenerOrEventListenerObject,
        options?: boolean | EventListenerOptions
    ): void {
        const doc = this.nativeDocument;
        if (doc) {
            doc.removeEventListener(type, listener, options);
        }
        else {

        }
    }
}

export { DocumentService };