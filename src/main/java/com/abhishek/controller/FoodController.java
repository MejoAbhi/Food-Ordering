package com.abhishek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.entity.Food;
import com.abhishek.entity.Restaurant;
import com.abhishek.entity.User;
import com.abhishek.request.CreateFoodRequest;
import com.abhishek.service.FoodService;
import com.abhishek.service.RestaurantService;
import com.abhishek.service.UserService;

@RestController
@RequestMapping("/api/food")
public class FoodController {

	@Autowired
	private FoodService foodService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	
	@GetMapping("/search")
	public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user=userService.findUSerByJwtToken(jwt);
		
		List<Food> food=foodService.serachFood(name);
		
		return new ResponseEntity<>(food,HttpStatus.CREATED);
	}
	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity<List<Food>> getRestaurantFood(@RequestParam boolean vegetarian,
			@RequestParam boolean seasonal,
			@RequestParam boolean nonveg,
			@RequestParam(required = false) String food_category,
			@PathVariable Long restaurantId,
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user=userService.findUSerByJwtToken(jwt);
		
		List<Food> food=foodService.getRestaurantsFood(restaurantId, vegetarian, nonveg, seasonal, food_category);
		
		return new ResponseEntity<>(food,HttpStatus.OK);
	}
}
