package com.business_analytics.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.business_analytics.model.dto.LoginRequestDTO;
import com.business_analytics.model.dto.RegisterRequestDTO;
import com.business_analytics.model.dto.TokenResponse;
import com.business_analytics.model.entity.Token;
import com.business_analytics.model.entity.Token.TokenType;
import com.business_analytics.model.entity.User;
import com.business_analytics.repository.TokenRepository;
import com.business_analytics.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public TokenResponse register(RegisterRequestDTO request) {
    var user = User.builder()
        .email(request.email())
        .password(passwordEncoder.encode(request.password()))
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();
    var savedUser = userRepository.save(user);
    var jwtToken = jwtService.generateToken(savedUser);
    var refreshToken = jwtService.generateRefreshToken(savedUser);
    saveUserToken(savedUser, jwtToken, TokenType.BEARER);
    saveUserToken(savedUser, refreshToken, TokenType.REFRESH);
    return new TokenResponse(jwtToken, refreshToken, savedUser);
  }

  public TokenResponse login(LoginRequestDTO request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.email(), request.password()));

    var user = userRepository.findByEmail(request.email()).orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);

    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken, TokenType.BEARER);
    saveUserToken(user, refreshToken, TokenType.REFRESH);
    return new TokenResponse(jwtToken, refreshToken, user);
  }

  private void saveUserToken(User user, String jwtToken, Token.TokenType tokenType) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(tokenType)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);

  }

  private void revokeAllAccessTokens(final User user) {
    final List<Token> validTokens = tokenRepository
        .findAllByUserIdAndTokenTypeAndRevokedFalseAndExpiredFalse(user.getId(), TokenType.BEARER);
    revokeTokens(validTokens);
  }

  private void revokeAllUserTokens(final User user) {
    final List<Token> validTokens = tokenRepository.findAllValidIsFalseAndRevokedIsFalseByUserId(user.getId());
    revokeTokens(validTokens);
  }

  private void revokeTokens(List<Token> tokens) {

    if (!tokens.isEmpty()) {
      for (final Token token : tokens) {
        token.setExpired(true);
        token.setRevoked(true);
      }
      tokenRepository.saveAll(tokens);
    }

  }

  public TokenResponse refreshToken(final String authHeader) {
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      throw new IllegalArgumentException("Invalid Bearer Token");
    }

    final String refreshToken = authHeader.substring(7);
    final String userEmail = jwtService.extractUsername(refreshToken);

    if (userEmail == null) {
      throw new IllegalArgumentException("Invalid Refresh Token");
    }

    final User user = userRepository.findByEmail(userEmail)
        .orElseThrow(() -> new UsernameNotFoundException(userEmail));

    if (!jwtService.isValidToken(refreshToken, user)) {
      throw new IllegalArgumentException("Invalid Refresh Token");
    }

    final Token storedToken = tokenRepository.findByToken(refreshToken).orElse(null);
    if (storedToken == null
        || storedToken.getTokenType() != Token.TokenType.REFRESH
        || storedToken.isRevoked()
        || storedToken.isExpired()) {
      throw new IllegalArgumentException("Refresh Token has been revoked");
    }

    final String accessToken = jwtService.generateToken(user);
    revokeAllAccessTokens(user);
    saveUserToken(user, accessToken, TokenType.BEARER);

    return new TokenResponse(accessToken, refreshToken, user);
  }

}
