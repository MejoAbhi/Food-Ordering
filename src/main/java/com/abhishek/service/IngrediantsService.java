package com.abhishek.service;

import java.util.List;

import com.abhishek.entity.IngrediantsCatagory;
import com.abhishek.entity.IngrediantsItem;

public interface IngrediantsService {

	public IngrediantsCatagory createIngrediantCategory(String name, Long restaurantId)throws Exception;
	
	public IngrediantsCatagory findIngrediantCatagoryById(Long id)throws Exception;
	
	public List<IngrediantsCatagory> findIngrediantsCatagoryByRestaurantId(Long id)throws Exception;
	
	public IngrediantsItem createIngrediantItem(Long restaurantId, String ingrediantName, Long categoryId) throws Exception;
	
	public List<IngrediantsItem> findRestaurantIngrediants(Long restaurantId);
	
	public IngrediantsItem updateStock(Long id)throws Exception;
}
