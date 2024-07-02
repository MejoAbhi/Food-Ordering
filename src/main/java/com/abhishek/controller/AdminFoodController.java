package com.abhishek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.entity.Food;
import com.abhishek.entity.Restaurant;
import com.abhishek.entity.User;
import com.abhishek.request.CreateFoodRequest;
import com.abhishek.response.MessageResponse;
import com.abhishek.service.FoodService;
import com.abhishek.service.RestaurantService;
import com.abhishek.service.UserService;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	
	@PostMapping("")
	public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
			@RequestHeader("Authorization") String jwt) throws Exception{
		User user=userService.findUSerByJwtToken(jwt);
		Restaurant restaurant=restaurantService.findRestaurantById(req.getRestaurantId());
		Food food=foodService.createFood(req, req.getCatagory(), restaurant);
		return new ResponseEntity<>(food,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
										@RequestHeader("Authorization") String jwt
										) throws Exception{
			User user=userService.findUSerByJwtToken(jwt);
			foodService.deleteFood(id);
			MessageResponse response=new MessageResponse();
			response.setMessage("Food deleted successfully");
			return new ResponseEntity<>(response,HttpStatus.OK);
			}

	
	@PutMapping("/{id}")
	public ResponseEntity<Food> updateFoodAvailibilityStatus(@PathVariable Long id,
			@RequestHeader("Authorization") String jwt
									) throws Exception{
		
		User user=userService.findUSerByJwtToken(jwt);
		
	Food food=foodService.updateAvailibilityStatus(id);
	return new ResponseEntity<>(food, HttpStatus.CREATED);
		}

}


