/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.components;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

@Component
public class JwtService {

    private static final String SECRET_KEY = "11111111111111111111111111111111";
    private static final byte[] SHARED_SECRET_KEY = SECRET_KEY.getBytes();
    private static final int EXPIRE_TIME = 86400000; // 24 hours in milliseconds

    public String generateTokenLogin(String username) {
        try {
            JWSSigner signer = new MACSigner(SHARED_SECRET_KEY);

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .claim("username", username)
                    .expirationTime(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                    .build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);
            return signedJWT.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException("Error generating JWT token", e);
        }
    }

    private JWTClaimsSet getClaimsFromToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SHARED_SECRET_KEY);
            if (signedJWT.verify(verifier)) {
                return signedJWT.getJWTClaimsSet();
            } else {
                throw new RuntimeException("JWT token verification failed");
            }
        } catch (JOSEException | ParseException e) {
            throw new RuntimeException("Error parsing JWT token", e);
        }
    }

    private Date getExpirationDateFromToken(String token) {
        JWTClaimsSet claims = getClaimsFromToken(token);
        return claims.getExpirationTime();
    }

    public String getUsernameFromToken(String token) {
        try {
            JWTClaimsSet claims = getClaimsFromToken(token);
            return claims.getStringClaim("username");
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving username from JWT token", e);
        }
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Boolean validateTokenLogin(String token) {
        if (token == null || token.trim().isEmpty()) {
            return false;
        }
        try {
            String username = getUsernameFromToken(token);
            return username != null && !username.isEmpty() && !isTokenExpired(token);
        } catch (Exception e) {
            // Log exception details if needed
            return false;
        }
    }
}
