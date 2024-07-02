package com.abhishek.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.entity.Catagory;
import com.abhishek.entity.Food;
import com.abhishek.entity.Restaurant;
import com.abhishek.repository.FoodRepository;
import com.abhishek.request.CreateFoodRequest;

@Service
public class FoodServiceImp  implements FoodService{
	
	@Autowired
	private FoodRepository foodRepository;

	@Override
	public Food createFood(CreateFoodRequest req, Catagory catagory, Restaurant restaurant) {
		// TODO Auto-generated method stub
		Food food=new Food();
	    food.setFoodCatagory(catagory);
	    food.setRestaureant(restaurant);
	    food.setDescription(req.getDescription());
	    food.setImages(req.getImages());
	    food.setName(req.getName());
	    food.setPrice(req.getPrice());
	    food.setIngrediants(req.getIngrediants());
	    food.setSeasonal(req.isSeasional());
	    food.setVeg(req.isVegetarin());
	    
	    Food saveFood=foodRepository.save(food);
	    restaurant.getFoods().add(saveFood);
		return saveFood;
	}
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	@Override
	public void deleteFood(Long foodId) throws Exception {
		// TODO Auto-generated method stub
		Food food=findFoodById(foodId);
		food.setRestaureant(null);
		foodRepository.save(food);
		
	}
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	@Override
	public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegitarain, boolean isNonveg, boolean isSeasional,
			String foodCategory) {
		// TODO Auto-generated method stub
		List<Food> foods=foodRepository.findByRestaureantId(restaurantId);
		
		//if satisfied then it will go for the methed written into the if condition
		if(isVegitarain) {
			
			foods=filterByVegetarian(foods,isVegitarain);
		}
		if(isNonveg) {
			foods=filterByNonVeg(foods,isNonveg);
		}
		if(isSeasional) {
			foods=filterBySeasonal(foods,isSeasional);
		}
		if(foodCategory!=null && !foodCategory.equals("")) {
			foods=filterByCatagory(foods,foodCategory);
		}
		return foods;
	}
	

	private List<Food> filterByCatagory(List<Food> foods, String foodCategory) {
		// TODO Auto-generated method stub
		return foods.stream().filter(food ->{
			//If the Foodcatagory matches with the Comming request from the user
			if(food.getFoodCatagory()!=null) {
				return food.getFoodCatagory().getName().equals(foodCategory);
			}
			return false;
		}).collect(Collectors.toList());
	}
	

	private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasional) {
		// TODO Auto-generated method stub
		return foods.stream().filter(food -> food.isSeasonal()==isSeasional).collect(Collectors.toList());
	}
	

	private List<Food> filterByNonVeg(List<Food> foods, boolean isNonveg) {
		// TODO Auto-generated method stub
		return foods.stream().filter(food -> food.isVeg()==false).collect(Collectors.toList());
	}
	

	private List<Food> filterByVegetarian(List<Food> foods, boolean isVegitarain) {
		// TODO Auto-generated method stub
		return foods.stream().filter(food -> food.isVeg()==isVegitarain).collect(Collectors.toList());
	}
	
	
	
	
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	@Override
	public List<Food> serachFood(String keyword) {
		// TODO Auto-generated method stub
		return foodRepository.searchFood(keyword);
	}
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	@Override
	public Food findFoodById(Long foodId) throws Exception {
		// TODO Auto-generated method stub
		Optional<Food> optionalFood=foodRepository.findById(foodId);
		if(optionalFood.isEmpty()) {
			throw new Exception("food Not exist...");
		}
		
		return optionalFood.get();
	}
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//If food is not in stock then add food
	@Override
	public Food updateAvailibilityStatus(Long foodId) throws Exception {
		// TODO Auto-generated method stub
		Food food=findFoodById(foodId);
		food.setAvailable(!food.isAvailable());
		return foodRepository.save(food);
	}

}
