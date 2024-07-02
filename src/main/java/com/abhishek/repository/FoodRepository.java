package com.abhishek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abhishek.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {

	List<Food> findByRestaureantId(Long restaureantId);
	
	@Query("SELECT f FROM Food f WHERE f.name LIKE %:keyword% OR f.foodCatagory.name LIKE %:keyword%")
	List<Food> searchFood(@Param("keyword") String leyword);
}
