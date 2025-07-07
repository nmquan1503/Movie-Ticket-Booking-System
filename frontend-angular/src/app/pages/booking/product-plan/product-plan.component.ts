import { Component, Input, OnInit } from "@angular/core";
import { ProductDetailResponse } from "../../../core/models/responses/product/product-detail-response.model";
import { CommonModule } from "@angular/common";

@Component({
    standalone: true,
    selector: 'product-plan',
    templateUrl: './product-plan.component.html',
    styleUrls: [
        './product-plan.component.scss'
    ],
    imports: [
        CommonModule
    ]
})
export class ProductPlanComponent implements OnInit {
    @Input() products!: ProductDetailResponse[];
    @Input() productQuantity: Map<number, number> = new Map();

    ngOnInit(): void {
        if (this.products) {
            for (let product of this.products) {
                this.productQuantity.set(product.id, 0);
            }
        }
    }

    increase(productId: number) {
        let quantity = this.productQuantity.get(productId);
        quantity = quantity ? quantity : 0;
        if (quantity >= 20) {
            return;
        }
        this.productQuantity.set(productId, quantity + 1);
    }

    decrease(productId: number) {
        let quantity = this.productQuantity.get(productId);
        quantity = quantity ? quantity : 0;
        if (quantity <= 0) {
            return;
        }
        this.productQuantity.set(productId, quantity - 1);
    }

}