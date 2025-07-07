import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { PaymentTransactionService } from "../../core/services/payment/payment-transaction.service";
import { ActivatedRoute, RouterModule } from "@angular/router";
import { LoadingOverlayComponent } from "../../shared/components/loading-overlay/loading-overlay.component";

@Component({
    standalone: true,
    selector: 'payment-result-page',
    templateUrl: './payment-result-page.component.html',
    styleUrls: [
        './payment-result-page.component.scss'
    ],
    imports: [
        CommonModule,
        LoadingOverlayComponent,
        RouterModule
    ]
})
export class PaymentResultPageComponent implements OnInit {

    status: 'success' | 'fail' | 'loading' = 'loading';
    private alreadyHandled: boolean = false;

    constructor(
        private route: ActivatedRoute,
        private paymentTransactionService: PaymentTransactionService
    ) { }

    ngOnInit(): void {
        this.route.queryParams.subscribe(async (params) => {
            if (this.alreadyHandled) {
                return;
            }
            this.alreadyHandled = true;
            this.paymentTransactionService.handleReturn(params).subscribe({
                next: (response) => {
                    if (response.success) {
                        this.status = 'success';
                    }
                    else {
                        console.log(`api error: ${response.message}`);
                        this.status = 'fail';
                    }
                },
                error: (err) => {
                    console.log(err.error.message);
                    this.status = 'fail';
                }
            })
        })
    }

}