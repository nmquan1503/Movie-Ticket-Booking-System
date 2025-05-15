package com.nmquan1503.backend_springboot.entities.payment;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "payment_methods")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Byte id;

    @Column(nullable = false)
    String name;

    @Column(name = "thumbnail_url")
    String thumbnailURL;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    PaymentMethodStatus status;

}
