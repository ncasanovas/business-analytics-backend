package com.business_analytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.business_analytics.model.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long>{
  
}
