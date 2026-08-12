package com.business_analytics.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.business_analytics.model.entity.Token;
import com.business_analytics.model.entity.Token.TokenType;

public interface TokenRepository extends JpaRepository<Token, Long> {

  Optional<Token> findByToken(String token);

  List<Token> findAllValidIsFalseAndRevokedIsFalseByUserId(Long id);

  List<Token> findAllByUserIdAndTokenTypeAndRevokedFalseAndExpiredFalse(Long id, TokenType tokenType);

}
