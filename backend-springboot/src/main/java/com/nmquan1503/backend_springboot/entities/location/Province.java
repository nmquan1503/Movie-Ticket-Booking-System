package com.nmquan1503.backend_springboot.entities.location;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "provinces")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Province {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Short id;
    
    @Column(nullable = false)
    String name;

}
