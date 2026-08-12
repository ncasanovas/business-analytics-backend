package com.business_analytics.service;

import org.springframework.stereotype.Service;

import com.business_analytics.model.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtService {

  @Value("${application.security.jwt.secret-key}")
  private String secretKey;

  @Value("${application.security.jwt.expiration}")
  private long jwtExpiration;

  @Value("${application.security.jwt.refresh-token.expiration}")
  private long refreshTokenExpiration;

  public String generateToken(final User user) {
    return buildToken(user, jwtExpiration);
  }

  public String generateRefreshToken(final User user) {
    return buildToken(user, refreshTokenExpiration);
  }

  private String buildToken(final User user, final long expiration) {
    return Jwts.builder()
        .id(user.getId().toString())
        .claims(Map.of("name", user.getEmail()))
        .subject(user.getEmail())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(getSignInKey())
        .compact();

  }

  public boolean isValidToken(final String token, final User user) {
    final String username = extractUsername(token);
    return (username.equals(user.getEmail())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(final String token) {
    return extractExpiration(token).before(new Date(System.currentTimeMillis()));

  }

  public String extractUsername(final String token) {
    return extractClaims(token).getSubject();

  }

  public Date extractExpiration(final String token) {
    return extractClaims(token).getExpiration();
  }

  private Claims extractClaims(final String token) {
    return Jwts.parser()
        .verifyWith(getSignInKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();

  }

  private SecretKey getSignInKey() {

    byte[] keyBytes = Decoders.BASE64.decode(secretKey);

    return Keys.hmacShaKeyFor(keyBytes);
  }

}