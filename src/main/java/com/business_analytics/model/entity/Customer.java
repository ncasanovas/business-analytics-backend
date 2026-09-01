package com.business_analytics.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@Table(name = "customers")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;
  private String password;
  private String name;
  private LocalDateTime createdAt;

  @ToString.Exclude
  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  private List<Order> orders;

  @ToString.Exclude
  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  private List<Event> events;

}
