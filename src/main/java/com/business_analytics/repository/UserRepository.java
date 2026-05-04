package com.business_analytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business_analytics.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  
}
