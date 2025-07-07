import { CommonModule } from "@angular/common";
import { Component, Input, OnDestroy, OnInit } from "@angular/core";
import dayjs, { Dayjs } from "dayjs";
import { interval, Subscription } from "rxjs";

@Component({
    standalone: true,
    selector: 'count-down-checkout',
    templateUrl: './count-down-checkout.component.html',
    styleUrls: [
        './count-down-checkout.component.scss'
    ],
    imports: [
        CommonModule
    ]
})
export class CountDownCheckoutComponent implements OnInit, OnDestroy {
    @Input() endTime!: string | Dayjs;

    remainingTime: string | null = null;
    private timeSub?: Subscription;

    ngOnInit(): void {
        this.updateRemainingTime();
        this.timeSub = interval(1000).subscribe(() => this.updateRemainingTime());
    }

    ngOnDestroy(): void {
        this.timeSub?.unsubscribe();
    }

    private updateRemainingTime() {
        const now = dayjs();
        const end = typeof this.endTime === 'string' ? dayjs(this.endTime) : this.endTime;

        const diff = end.diff(now);
        if (diff < 0) {
            this.remainingTime = null;
            this.timeSub?.unsubscribe();
            return;
        }

        const minutes = Math.floor(diff / 60000);
        const seconds = Math.floor((diff % 60000) / 1000);
        this.remainingTime = `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
    }

}