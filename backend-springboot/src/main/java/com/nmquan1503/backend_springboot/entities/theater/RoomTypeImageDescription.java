package com.nmquan1503.backend_springboot.entities.theater;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "room_type_image_descriptions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomTypeImageDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "room_type_image_id", nullable = false)
    RoomTypeImage roomTypeImage;

    @Column(nullable = false)
    String description;

}
