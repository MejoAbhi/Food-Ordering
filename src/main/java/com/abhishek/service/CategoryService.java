package com.abhishek.service;

import java.util.List;

import com.abhishek.entity.Catagory;

public interface CategoryService {
	//userId For which restaurant I want the category
	public Catagory createCategory(String name, Long userId)throws Exception;
	
	public List<Catagory> findCategoryByRestaurantId(Long id)throws Exception;
	
	public Catagory findCategoryById(Long id)throws Exception;
	
}
