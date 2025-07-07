import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { ReservationService } from "../../core/services/reservation/reservation.service";
import { ReservationDetailResponse } from "../../core/models/responses/reservation/reservation-detail-response.model";
import { ShowtimeListBannerComponent } from "../showtime-list/banner/showtime-list-banner.component";
import { CountDownCheckoutComponent } from "./count-down/count-down-checkout.component";
import { PaymentMethodService } from "../../core/services/payment/payment-method.service";
import { PaymentMethodDetailResponse } from "../../core/models/responses/payment/payment-method-detail-response.model";
import { ReservationInfoComponent } from "./reservation-info/reservation-info.component";
import { MethodListComponent } from "./method-list/method-list.component";

@Component({
    standalone: true,
    selector: 'checkout-page',
    templateUrl: './checkout-page.component.html',
    styleUrls: [
        './checkout-page.component.scss'
    ],
    imports: [
        CommonModule,
        ShowtimeListBannerComponent,
        CountDownCheckoutComponent,
        ReservationInfoComponent,
        MethodListComponent
    ]
})
export class CheckoutPageComponent implements OnInit {

    reservation!: ReservationDetailResponse;
    methods!: PaymentMethodDetailResponse[];

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private reservationService: ReservationService,
        private paymentMethodService: PaymentMethodService
    ) { }

    ngOnInit(): void {
        const reservationId = Number(this.route.snapshot.paramMap.get('reservationId'));
        if (!isNaN(reservationId)) {
            this.loadReservation(reservationId);
            this.loadMethods();
        }
        else {
            this.router.navigate(['/home']);
        }
    }

    private loadMethods(): void {
        this.paymentMethodService.getAllPaymentMethodDetails().subscribe({
            next: (response) => {
                if (response.success && response.data) {
                    this.methods = response.data;
                }
                else {
                    console.log(`api error: ${response.message}`);
                }
            },
            error: (err) => {
                console.log(`error: ${err.error.message}`);
            }
        });
    }

    private loadReservation(reservationId: number): void {
        this.reservationService.getReservationDetail(reservationId).subscribe({
            next: (response) => {
                if (response.success && response.data) {
                    this.reservation = response.data;
                }
                else {
                    console.log(`api error: ${response.message}`);
                    this.router.navigate(['/home']);
                }
            },
            error: (err) => {
                console.log(`error: ${err.error.message}`);
                this.router.navigate(['/home']);
            }
        });
    }

}