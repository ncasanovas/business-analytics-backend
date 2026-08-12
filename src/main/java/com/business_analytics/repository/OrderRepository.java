package com.business_analytics.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.business_analytics.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

   @Query("""
        SELECT DATE(o.createdAt), SUM(o.totalAmount)
        FROM Order o
        WHERE o.createdAt BETWEEN :from AND :to
        GROUP BY DATE(o.createdAt)
    """)
    List<Object[]> getRevenueByDate(LocalDateTime from, LocalDateTime to);
  
}
