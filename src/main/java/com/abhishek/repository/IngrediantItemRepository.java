package com.abhishek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishek.entity.IngrediantsItem;

public interface IngrediantItemRepository extends JpaRepository<IngrediantsItem, Long> {
	
	List<IngrediantsItem> findByRestaurantId(Long id);

}
