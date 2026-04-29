package com.business_analytics.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

  @Id @GeneratedValue
  private Integer id;

  private String email;
  private String password;
  private LocalDateTime createdAt;
  private LocalDateTime updtedAt;
  
}
