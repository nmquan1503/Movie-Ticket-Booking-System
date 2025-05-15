package com.nmquan1503.backend_springboot.services.reservation;

import com.nmquan1503.backend_springboot.dtos.requests.product.ProductOrderRequest;
import com.nmquan1503.backend_springboot.entities.product.Product;
import com.nmquan1503.backend_springboot.entities.reservation.Reservation;
import com.nmquan1503.backend_springboot.entities.reservation.ReservationProduct;
import com.nmquan1503.backend_springboot.repositories.reservation.ReservationProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationProductService {

    ReservationProductRepository reservationProductRepository;

    public void save(Reservation reservation, List<ProductOrderRequest> orders) {
        List<ReservationProduct> reservationProducts = orders.stream().map(
                order -> ReservationProduct.builder()
                        .reservation(reservation)
                        .product(Product.builder().id(order.getProductId()).build())
                        .quantity(order.getQuantity())
                        .build()
        ).toList();
        reservationProductRepository.saveAll(reservationProducts);
    }

    public List<ReservationProduct> fetchByReservationId(Long reservationId) {
        return reservationProductRepository.findByReservationId(reservationId);
    }

}
