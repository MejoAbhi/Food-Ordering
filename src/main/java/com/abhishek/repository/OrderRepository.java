package com.abhishek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishek.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	public List<Order> findByCustomerId(Long userId);
	
	public List<Order> findByrestaureantId(Long restaurantId);

}
