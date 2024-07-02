package com.abhishek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishek.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	public Cart findByCustomerId(Long userId);
}
