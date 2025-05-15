package com.nmquan1503.backend_springboot.services.authentication;

import com.nmquan1503.backend_springboot.entities.authentication.RefreshToken;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.repositories.authentication.RefreshTokenRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RefreshTokenService {

    @NonFinal
    @Value("${jwt.refreshToken.expirationTime}")
    int refreshTokenExpirationTime;

    RefreshTokenRepository refreshTokenRepository;

    public void save(RefreshToken refreshToken) {
        Optional<RefreshToken> existingToken = refreshTokenRepository.findByUserAndDeviceInfo(refreshToken.getUser(), refreshToken.getDeviceInfo());
        existingToken.ifPresent(refreshTokenRepository::delete);
        refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken fetchRefreshTokenById(String refreshTokenId) {
        return refreshTokenRepository.findById(refreshTokenId)
                .orElseThrow(() -> new GeneralException(ResponseCode.UNAUTHORIZED));
    }

    public void updateExpirationTime(RefreshToken refreshToken) {
        if (refreshToken != null) {
            refreshToken.setExpirationTime(
                    LocalDateTime.ofInstant(
                            Instant.now().plusSeconds(refreshTokenExpirationTime),
                            ZoneId.systemDefault()
                    )
            );
            refreshTokenRepository.save(refreshToken);
        }
    }

    public void deleteById(String refreshTokenId) {
        if (refreshTokenId != null) {
            refreshTokenRepository.delete(fetchRefreshTokenById(refreshTokenId));
        }
        else {
            throw new GeneralException(ResponseCode.UNAUTHORIZED);
        }
    }

    public void cleanExpiredRefreshTokens() {
        LocalDateTime now = LocalDateTime.now();
        List<RefreshToken> expiredRefreshTokens = refreshTokenRepository.findByExpirationTimeBefore(now);
        if (!expiredRefreshTokens.isEmpty()) {
            refreshTokenRepository.deleteAll(expiredRefreshTokens);
        }
    }
}
