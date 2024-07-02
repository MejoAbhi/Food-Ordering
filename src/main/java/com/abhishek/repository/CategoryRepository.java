package com.abhishek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishek.entity.Catagory;

public interface CategoryRepository extends JpaRepository<Catagory, Long>{
	
	public List<Catagory> findByRestaurantId(Long id);

}
