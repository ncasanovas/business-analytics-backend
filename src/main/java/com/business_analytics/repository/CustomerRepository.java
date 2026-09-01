package com.business_analytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.business_analytics.model.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
  
}
