import { Component, Input } from "@angular/core";
import { ReservationDetailResponse } from "../../../core/models/responses/reservation/reservation-detail-response.model";
import dayjs from "dayjs";
import { CommonModule } from "@angular/common";

@Component({
    standalone: true,
    selector: 'reservation-info',
    templateUrl: './reservation-info.component.html',
    styleUrls: [
        './reservation-info.component.scss'
    ],
    imports: [
        CommonModule
    ]
})
export class ReservationInfoComponent {
    @Input() reservation!: ReservationDetailResponse;
    dayjs = dayjs;

    get seatString(): string {
        return this.reservation.seats.map(seat => seat.name).join(", ");
    }

    get totalPrice(): number {
        let total = this.reservation.ticketPrice;
        for (let item of this.reservation.items) {
            total += item.quantity * item.product.price;
        }
        return total;
    }
}