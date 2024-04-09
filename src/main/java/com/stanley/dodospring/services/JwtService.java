package com.stanley.dodospring.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String extractUsername(String token);

    Date extractExpiration(String token);

    <T> T extractClaims(String token, Function<Claims, T> claimsResolver);

    Claims extractAllClaims(String token);

    String generateToken(UserDetails userDetails);

    String generateToken(Map<String, Object> claims, UserDetails userDetails);

    String buildToken(Map<String, Object> claims, UserDetails userDetails, long jwtExpiration);

    boolean isTokenValid(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);

    Key getSignInKey();
}
