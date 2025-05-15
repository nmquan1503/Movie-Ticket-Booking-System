package com.nmquan1503.backend_springboot.entities.product;

import com.nmquan1503.backend_springboot.entities.theater.Branch;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "product_branch")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BranchProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Short id;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    Branch branch;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    Product product;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    ProductStatus status;

}
