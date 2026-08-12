package com.business_analytics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.business_analytics.model.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException ex) {
    ErrorResponse error = new ErrorResponse(401, "Email o contraseña incorrectos");
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);

  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleUserNotFound(UsernameNotFoundException ex) {
    ErrorResponse error = new ErrorResponse(404, "Usuario no encontrado");
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
    ErrorResponse error = new ErrorResponse(400, ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

  }

}
