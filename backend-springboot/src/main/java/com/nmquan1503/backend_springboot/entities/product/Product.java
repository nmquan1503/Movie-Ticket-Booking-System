package com.nmquan1503.backend_springboot.entities.product;

import com.nmquan1503.backend_springboot.entities.reservation.Reservation;
import com.nmquan1503.backend_springboot.entities.reservation.ReservationProduct;
import com.nmquan1503.backend_springboot.entities.theater.Branch;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Byte id;

    @Column(nullable = false)
    String name;

    @Column(name = "thumbnail_url")
    String thumbnailURL;

    @Column(nullable = false)
    Double price;

}
