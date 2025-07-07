package com.nmquan1503.backend_springboot.entities.theater;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "room_type_features")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomTypeFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    RoomType roomType;

    @Column(name = "image_url")
    String imageURL;

    String title;

    String description;

}
