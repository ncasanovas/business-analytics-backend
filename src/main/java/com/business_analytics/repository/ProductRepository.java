package com.business_analytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.business_analytics.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  
}
