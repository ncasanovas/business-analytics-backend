package com.business_analytics.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.business_analytics.model.entity.Token;
import com.business_analytics.model.entity.User;
import com.business_analytics.model.entity.Token.TokenType;
import com.business_analytics.repository.TokenRepository;
import com.business_analytics.repository.UserRepository;
import com.business_analytics.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;
  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");

    final String jwtToken;
    String username;
    Token token;

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      jwtToken = authHeader.substring(7);
      username = jwtService.extractUsername(jwtToken);

      token = tokenRepository.findByToken(jwtToken).orElse(null);
    } catch (Exception e) {
      filterChain.doFilter(request, response);
      return;
    }

    if (token == null || token.isExpired() || token.isRevoked() || token.getTokenType() != TokenType.BEARER) {
      filterChain.doFilter(request, response);
      return;
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userDetailsService.loadUserByUsername(username);
      final Optional<User> user = userRepository.findByEmail(userDetails.getUsername());

      if (user.isEmpty()) {
        filterChain.doFilter(request, response);
        return;
      }
      if (jwtService.isValidToken(jwtToken, user.get())) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }

    filterChain.doFilter(request, response);
  }

}
