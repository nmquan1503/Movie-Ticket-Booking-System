package com.nmquan1503.backend_springboot.entities.payment;

import com.nmquan1503.backend_springboot.entities.reservation.Reservation;
import com.nmquan1503.backend_springboot.entities.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment_transactions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "transaction_id", nullable = false)
    String transactionId;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    PaymentMethod method;

    @Column(nullable = false)
    LocalDateTime time;

    @Column(nullable = false)
    Double amount;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    PaymentTransactionStatus status;

}
