package com.business_analytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.business_analytics.model.entity.OrderItems;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {

}
