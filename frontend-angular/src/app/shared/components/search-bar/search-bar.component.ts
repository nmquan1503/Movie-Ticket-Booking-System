import { Component, OnInit, ViewChild } from "@angular/core";
import { ProvinceDetailResponse } from "../../../core/models/responses/location/province-detail-response.model";
import { CommonModule } from "@angular/common";
import { ClickOutsideDirective } from "../../directives/click-outside.directive";
import { BranchOptionResponse } from "../../../core/models/responses/theater/branch-option-response.model";
import { BranchService } from "../../../core/services/theater/branch.service";
import { MatDatepicker, MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatNativeDateModule } from '@angular/material/core';
import { FormsModule } from "@angular/forms";
import { BaseComponent } from "../base/base.component";
import { LoaderService } from "../../../core/services/ui/loader.service";

@Component({
    standalone: true,
    selector: "search-bar",
    templateUrl: "./search-bar.component.html",
    styleUrls: [
        "./search-bar.component.scss"
    ],
    imports: [
        CommonModule,
        ClickOutsideDirective,
        MatDatepickerModule,
        MatFormFieldModule,
        MatInputModule,
        MatNativeDateModule,
        FormsModule
    ]
})
export class SearchBar {

    isProvinceListOpen: boolean = false;
    provinces: ProvinceDetailResponse[] = [];
    selectedProvince: ProvinceDetailResponse | null = null;

    isBranchListOpen: boolean = false;
    branches: BranchOptionResponse[] = [];
    selectedBranch: BranchOptionResponse | null = null;

    isDatePickerOpen: boolean = false;
    selectedDate: Date | null = null;

    @ViewChild('picker') datePicker!: MatDatepicker<Date>;
    @ViewChild('dateInput') dateInput: any;

    constructor(
        private branchService: BranchService
    ) { }

    ngOnInit(): void {
        this.loadBranches();
    }
    
    private loadBranches(): void {
        this.branchService.getAllBranchOptions().subscribe({
            next: (response) => {
                if (response.success === true) {
                    this.branches = response.data ?? [];
                    this.loadProvince();
                }
                else {
                    console.log(`code: ${response.code}\nmessage: ${response.message}`);
                }
            },
            error: (err) => {
                console.log(`error: ${err}`);
            }
        });
    }
    
    selectBranch(branch: BranchOptionResponse | null): void {
        this.selectedBranch = branch;
        this.isBranchListOpen = false;
    }

    get filteredBranches(): BranchOptionResponse[] {
        if (this.selectedProvince === null) {
            return this.branches;
        }
        return this.branches.filter(branch => branch.province.id === this.selectedProvince?.id);
    }

    toggleBranchList() {
        this.isBranchListOpen = !this.isBranchListOpen;
    }
    
    private loadProvince(): void {
        this.provinces = Array.from(
            new Map(
                this.branches.map(branch => [branch.province.id, branch.province])
            ).values()
        );
    }

    selectProvince(province: ProvinceDetailResponse | null): void {
        this.selectedProvince = province;
        this.isProvinceListOpen = false;
        if (this.selectedBranch?.province.id !== this.selectedProvince?.id) {
            this.selectedBranch = null;
        }
    }

    toggleProvinceList() {
        this.isProvinceListOpen = !this.isProvinceListOpen;
    }


    selectDate(date: Date): void {
        this.selectedDate = date;
        this.isDatePickerOpen = false;
        this.datePicker.close();
    }

    toggleDatePicker() {
        this.isDatePickerOpen = !this.isDatePickerOpen;
        if (this.isDatePickerOpen) {
        setTimeout(() => {
            this.datePicker.open();
        });
    }
    }
}