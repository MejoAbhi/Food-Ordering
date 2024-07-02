package com.abhishek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishek.entity.IngrediantsCatagory;

public interface IngrediantsCategoryRepository extends JpaRepository<IngrediantsCatagory, Long>{
	
	List<IngrediantsCatagory> findByRestaurantId(Long id);

}
