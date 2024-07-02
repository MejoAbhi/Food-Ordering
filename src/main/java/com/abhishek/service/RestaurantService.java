package com.abhishek.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abhishek.dto.RestaurantDto;
import com.abhishek.entity.Restaurant;
import com.abhishek.entity.User;
import com.abhishek.request.CreateRestaurantRequest;

@Service
public interface RestaurantService {
	
	public Restaurant createRestaurant(CreateRestaurantRequest req,User user);
	
	//UpdateResutant details
	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRequest)throws Exception;
	
	//Delete restaurant details
	public void deleteRestaurant(Long restaurantId) throws Exception;
	
	//getting all the restaurants(ONLY_ADMIN)
	public List<Restaurant> getAllRestaurants();
	
	//getting all restaurants by name
	public List<Restaurant> serachRestaurants(String keyword);
	
	//find restaurant by Id
	public Restaurant findRestaurantById(Long id) throws Exception;
	
	//getting restaurant by userId 
	public Restaurant geteRestaurantByUserId(Long userId) throws Exception;
	
	//
	public RestaurantDto addFavourites(Long restaurantId, User user)throws Exception;
	
	public Restaurant updateRestaurantStatus(Long id)throws Exception;
	

}
