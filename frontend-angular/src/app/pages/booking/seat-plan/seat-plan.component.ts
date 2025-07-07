import { Component, Input, OnInit, Output } from "@angular/core";
import { SeatDetailResponse } from "../../../core/models/responses/theater/seat-detail-response.model";
import { CommonModule } from "@angular/common";

@Component({
    standalone: true,
    selector: 'seat-plan',
    templateUrl: './seat-plan.component.html',
    styleUrls: [
        './seat-plan.component.scss'
    ],
    imports: [
        CommonModule
    ]
})
export class SeatPlanComponent implements OnInit {
    @Input() seats!: SeatDetailResponse[];
    @Input() choosedSeats!: SeatDetailResponse[];
    @Input() rowCount!: number;
    @Input() columnCount!: number;
    seatColorMap = new Map<string, string>([
        ['Thường', '#90a4ae'],        // Xám xanh nhạt (trung tính)
        ['VIP', '#ff6f00'],           // Cam cháy (nổi bật)
        ['4DX', '#2962ff'],           // Xanh dương đậm
        ['Gold', '#ffd600'],          // Vàng sáng chói
        ['Bed', '#8e24aa'],           // Tím đậm
        ['Premium', '#d50000'],       // Đỏ tươi
        ['BeanBag', '#00acc1'],       // Xanh ngọc lam (khác hẳn xanh lá)
        ['Sofa', '#5d4037'],          // Nâu đậm
        ['Sweetbox', '#ec407a'],      // Hồng đậm

        // Trạng thái ghế:
        ['Đang chọn', '#00e676'],     // Xanh lá mạ (tươi sáng)
        ['Đã bị chọn', '#c62828'],    // Đỏ đậm (máu đông)
    ]);

    disableSeat: number[] = [];

    ngOnInit(): void {
        if (this.seats) {
            for (let seat of this.seats) {
                if (seat.isLocked) {
                    this.disableSeat.push(seat.id);
                }
            }
        }
    }

    getSeatColor(seat: SeatDetailResponse): string {
        let color;
        if (seat.isLocked) {
            color = this.seatColorMap.get('Đã bị chọn');
        }
        else if (this.choosedSeats.some(s => s.id === seat.id)) {
            color = this.seatColorMap.get('Đang chọn');
        } 
        else {
            color = this.seatColorMap.get(seat.type.name);
        }
        return color ? color : '#c0392b';
    }

    isHoverable(seatId: number): boolean {
        return !this.disableSeat.some(id => id === seatId);
    }

    chooseSeat(seat: SeatDetailResponse): void {
        if (seat.isLocked) {
            return;
        }
        const idChoosed = this.choosedSeats.findIndex(s => s.id === seat.id);
        if (idChoosed !== -1) {
            this.choosedSeats.splice(idChoosed, 1);
            const idDisable = this.disableSeat.findIndex(id => id === seat.id);
            if (idDisable !== -1) {
                this.disableSeat.splice(idDisable, 1);
            }
            return;
        }
        if (this.choosedSeats.length >= 6) {
            alert('Chỉ được chọn tối đa 6 ghế');
            return;
        }
        this.choosedSeats.push(seat);
        this.disableSeat.push(seat.id);
    }

}