package com.nmquan1503.backend_springboot.repositories.payment;

import com.nmquan1503.backend_springboot.entities.payment.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Byte> {
}
