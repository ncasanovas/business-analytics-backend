package com.business_analytics.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "events")
public class Event {

  @Id
  @GeneratedValue
  private Integer id;

  private String type;

  @Column(name = "user_id")
  private Integer userId;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

}
