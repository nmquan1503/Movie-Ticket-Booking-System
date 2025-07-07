package com.nmquan1503.backend_springboot.mappers.reservation;

import com.nmquan1503.backend_springboot.dtos.responses.product.ProductReservationItemResponse;
import com.nmquan1503.backend_springboot.entities.product.Product;
import com.nmquan1503.backend_springboot.entities.reservation.Reservation;
import com.nmquan1503.backend_springboot.entities.reservation.ReservationProduct;
import com.nmquan1503.backend_springboot.mappers.product.ProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {
                ProductMapper.class
        }
)
public interface ReservationProductMapper {

    ProductReservationItemResponse toProductReservationItemResponse(ReservationProduct reservationProduct);

    List<ProductReservationItemResponse> toListProductReservationItemResponse(List<ReservationProduct> reservationProducts);

}
