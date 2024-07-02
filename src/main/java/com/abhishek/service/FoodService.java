package com.abhishek.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abhishek.entity.Catagory;
import com.abhishek.entity.Food;
import com.abhishek.entity.Restaurant;
import com.abhishek.request.CreateFoodRequest;

@Service
public interface FoodService {

	public Food createFood(CreateFoodRequest req, Catagory catagory, Restaurant restaurant);
	
	void deleteFood(Long foodId) throws Exception;
	
	public List<Food> getRestaurantsFood(Long restaurantId,
										boolean isVegitarain,
										boolean isNonveg, 
										boolean isSeasional, 
										String foodCategory);
	public List<Food> serachFood(String keyword);
	
	public Food findFoodById(Long foodId) throws Exception;
	
	public Food updateAvailibilityStatus(Long foodId)throws Exception;
	
	
	
}
