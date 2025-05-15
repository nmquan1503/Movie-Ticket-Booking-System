package com.nmquan1503.backend_springboot.repositories.authentication;

import com.nmquan1503.backend_springboot.entities.authentication.RefreshToken;
import com.nmquan1503.backend_springboot.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

    Optional<RefreshToken> findByUserAndDeviceInfo(User user, String deviceInfo);

    List<RefreshToken> findByExpirationTimeBefore(LocalDateTime expirationTime);

}
