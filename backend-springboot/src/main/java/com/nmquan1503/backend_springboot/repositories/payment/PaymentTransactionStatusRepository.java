package com.nmquan1503.backend_springboot.repositories.payment;

import com.nmquan1503.backend_springboot.entities.payment.PaymentTransaction;
import com.nmquan1503.backend_springboot.entities.payment.PaymentTransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentTransactionStatusRepository extends JpaRepository<PaymentTransactionStatus, Byte> {

    Optional<PaymentTransactionStatus> findByName(String name);

}
