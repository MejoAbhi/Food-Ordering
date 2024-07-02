package com.abhishek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishek.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
