package com.nmquan1503.backend_springboot.entities.authentication;

import com.nmquan1503.backend_springboot.entities.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_tokens")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshToken {

    @Id
    String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    String deviceInfo;

    @Column(name = "expiration_time", nullable = false)
    LocalDateTime expirationTime;

}
