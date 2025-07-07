package com.nmquan1503.backend_springboot.entities.theater;

import com.nmquan1503.backend_springboot.entities.ticket.TicketPrice;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "room_types")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Byte id;

    @Column(nullable = false)
    String name;

    @Column(name = "extra_fee", nullable = false)
    Double extraFee;

    @Column(name = "icon_url")
    String iconURL;

    String slogan;

    @Column(name = "thumbnail_url")
    String thumbnailURL;

    String overview;

}
