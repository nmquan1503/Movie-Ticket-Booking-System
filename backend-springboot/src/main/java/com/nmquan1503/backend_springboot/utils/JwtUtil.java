package com.nmquan1503.backend_springboot.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nmquan1503.backend_springboot.entities.authentication.RefreshToken;
import com.nmquan1503.backend_springboot.entities.user.Role;
import com.nmquan1503.backend_springboot.entities.user.User;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtUtil {

    @Value("${jwt.signerKey}")
    String SIGNER_KEY;

    @Value("${jwt.accessToken.expirationTime}")
    int accessTokenExpirationTime;

    @Value("${jwt.refreshToken.expirationTime}")
    int refreshTokenExpirationTime;



    public RefreshToken generateRefreshToken(User user, String deviceInfo) {
        return RefreshToken.builder()
                .id(UUID.randomUUID().toString())
                .user(user)
                .deviceInfo(deviceInfo != null ? deviceInfo : "Unknown Device")
                .expirationTime(
                        LocalDateTime.ofInstant(
                                Instant.now().plusSeconds(refreshTokenExpirationTime),
                                ZoneId.systemDefault()
                        )
                )
                .build();
    }

    public String generateAccessToken(User user, List<Role> roles) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getId().toString())
                .issuer("nmquan1503")
                .issueTime(new Date())
                .expirationTime(Date.from(
                        Instant.now().plusSeconds(accessTokenExpirationTime)
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", generateScope(user, roles))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        }
        catch (JOSEException exception) {
            throw new GeneralException(ResponseCode.TOKEN_SIGNING_ERROR, exception);
        }
    }

    private String generateScope(User user, List<Role> roles) {
        StringJoiner str = new StringJoiner(" ");
//        if (!CollectionUtils.isEmpty(user.getRoles())) {
//            for (Role role : user.getRoles()) {
//                str.add("ROLE_" + role.getName());
//            }
//        }
        for (Role role : roles) {
            str.add("ROLE_" + role.getName());
        }
        return str.toString();
    }

    public boolean verifyToken(String token) {
        try {
            JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes());
            SignedJWT signedJWT = SignedJWT.parse(token);
            Date exp = signedJWT.getJWTClaimsSet().getExpirationTime();
            return signedJWT.verify(jwsVerifier) && exp.after(new Date());
        }
        catch (JOSEException | ParseException exception) {
            throw new GeneralException(ResponseCode.UNAUTHENTICATED, exception);
        }
    }

    public JWTClaimsSet getClaimSetFromToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet();
        }
        catch (ParseException exception) {
            throw new GeneralException(ResponseCode.UNAUTHENTICATED, exception);
        }
    }

}
