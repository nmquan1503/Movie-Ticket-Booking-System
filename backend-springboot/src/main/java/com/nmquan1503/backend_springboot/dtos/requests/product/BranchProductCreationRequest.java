package com.nmquan1503.backend_springboot.dtos.requests.product;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BranchProductCreationRequest {

    @NotNull(message = "BRANCH_EMPTY")
    Short branchId;

    @NotNull(message = "PRODUCT_EMPTY")
    Byte productId;

    @NotNull(message = "PRODUCT_STATUS_EMPTY")
    Byte productStatusId;

}
