package com.nmquan1503.backend_springboot.entities.user;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "genders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Gender {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Byte id;

    @Column(nullable = false)
    String name;

}
