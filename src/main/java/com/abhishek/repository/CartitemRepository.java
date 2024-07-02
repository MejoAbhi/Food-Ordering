package com.abhishek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishek.entity.CartItems;

public interface CartitemRepository extends JpaRepository<CartItems, Long> {

}
