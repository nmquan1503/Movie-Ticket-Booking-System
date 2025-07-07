import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class LoaderService {
    
    private loadingCount: number = 0;
    private isActive = new BehaviorSubject<boolean>(false);

    readonly isActiveObservable = this.isActive.asObservable();

    show(): void {
        this.loadingCount++;
        if (this.loadingCount === 1) {
            this.isActive.next(true);
        }
    }

    hide(): void {
        if (this.loadingCount > 0) {
            this.loadingCount--;
        }
        if (this.loadingCount === 0) {
            // setTimeout(() => this.isActive.next(false), 0);
        }
    }
}