package com.nmquan1503.backend_springboot.entities.theater;

import com.nmquan1503.backend_springboot.entities.location.Ward;
import com.nmquan1503.backend_springboot.entities.product.BranchProduct;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "branches")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Short id;

    @Column(nullable = false)
    String name;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    Ward ward;

    @Column(name = "specific_address")
    String specificAddress;

    @ManyToOne
    @JoinColumn(name = "status_id")
    BranchStatus status;

}
