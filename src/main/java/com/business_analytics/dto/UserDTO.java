package com.business_analytics.dto;

import java.time.LocalDateTime;

public class UserDTO {

  private String email;
  private String password;
  private LocalDateTime createdAt;
  private LocalDateTime updtedAt;

  public UserDTO(String email, String password, LocalDateTime createdAt) {
    this.email = email;
    this.password = password;
    this.createdAt = createdAt;
  }

  // Getters and Setters
  public String getEmail() {
    return email;
  }

  public void setName(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

   public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
