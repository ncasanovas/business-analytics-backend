package com.business_analytics.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business_analytics.model.dto.LoginRequestDTO;
import com.business_analytics.model.dto.RegisterRequestDTO;
import com.business_analytics.model.dto.TokenResponse;
import com.business_analytics.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/register")
  public ResponseEntity<TokenResponse> register(@RequestBody RegisterRequestDTO request) {
    final TokenResponse token = authService.register(request);
    return ResponseEntity.ok(token);

  }

  @PostMapping("/login")
  public ResponseEntity<TokenResponse> login(@RequestBody LoginRequestDTO request) {
    final TokenResponse token = authService.login(request);
    return ResponseEntity.ok(token);

  }

}
