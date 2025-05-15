package com.nmquan1503.backend_springboot.entities.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.nmquan1503.backend_springboot.entities.location.Ward;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "full_name", nullable = false)
    String fullName;

    @Column(name = "avatar_url")
    String avatarURL;

    @Column(nullable = false)
    String phone;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    LocalDate birthday;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id")
    Ward ward;

    @Column(name = "specific_address")
    String specificAddress;

    @Column(name = "creation_time", nullable = false, updatable = false)
    LocalDateTime creationTime;

}
