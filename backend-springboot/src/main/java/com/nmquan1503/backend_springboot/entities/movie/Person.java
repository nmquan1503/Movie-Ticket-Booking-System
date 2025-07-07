package com.nmquan1503.backend_springboot.entities.movie;

import com.nmquan1503.backend_springboot.entities.user.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "people")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    Gender gender;

    @Column(nullable = false)
    String name;

    @Column(name = "avatar_url")
    String avatarURL;
}
