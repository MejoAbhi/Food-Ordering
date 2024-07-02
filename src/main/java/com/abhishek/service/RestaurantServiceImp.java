package com.abhishek.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.dto.RestaurantDto;
import com.abhishek.entity.Address;
import com.abhishek.entity.Restaurant;
import com.abhishek.entity.User;
import com.abhishek.repository.AddressRepository;
import com.abhishek.repository.RestaurantRepository;
import com.abhishek.repository.UserRepository;
import com.abhishek.request.CreateRestaurantRequest;

@Service
public class RestaurantServiceImp implements RestaurantService{
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
		// TODO Auto-generated method stub
		Address address=addressRepository.save(req.getAddress());
		Restaurant restaurant=new Restaurant();
		restaurant.setAddress(address);
		restaurant.setContactInfo(req.getContactInfo());
		restaurant.setCuisineType(req.getCuisineType());
		restaurant.setDescription(req.getDescription());
		restaurant.setImages(req.getImages());
		restaurant.setName(req.getName());
		restaurant.setOpeningHours(req.getOpeningHours());
		restaurant.setRegistrationDate(LocalDateTime.now());
		restaurant.setOwner(user);
		
		
		return restaurantRepository.save(restaurant);
	}

	@Override
	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRequest) throws Exception {
		// TODO Auto-generated method stub
		Restaurant restaurant=findRestaurantById(restaurantId);
		if(restaurant.getCuisineType()!=null) {
			restaurant.setCuisineType(updateRequest.getCuisineType());
		}
		if(restaurant.getDescription()!=null) {
			restaurant.setDescription(updateRequest.getDescription());
		}
		if(restaurant.getName()!=null) {
			restaurant.setName(updateRequest.getName());
		}
		
		return restaurantRepository.save(restaurant);
	}

	@Override
	public void deleteRestaurant(Long restaurantId) throws Exception {
		// TODO Auto-generated method stub
		
		Restaurant restaurant=findRestaurantById(restaurantId);
		restaurantRepository.delete(restaurant);
		
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		// TODO Auto-generated method stub
		
		return restaurantRepository.findAll();
	}

	@Override
	public List<Restaurant> serachRestaurants(String keyword) {
		// TODO Auto-generated method stub
		return restaurantRepository.findBySearchQuery(keyword);
	}

	@Override
	public Restaurant findRestaurantById(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<Restaurant> opt= restaurantRepository.findById(id);
		
		if(opt.isEmpty()) {
			throw new Exception("Resturent not found on that Id");
		}
		
		return opt.get();
	}

	@Override
	public Restaurant geteRestaurantByUserId(Long userId) throws Exception {
		// TODO Auto-generated method stub
		Restaurant restaurant=restaurantRepository.findByOwnerId(userId);
		if(restaurant==null) {
			throw new Exception("Restaurant Not found with owner name"+userId);
		}
		return restaurant;
	}

	@Override
	public RestaurantDto addFavourites(Long restaurantId, User user) throws Exception {
		// TODO Auto-generated method stub
		Restaurant restaurant=findRestaurantById(restaurantId);
		RestaurantDto dto=new RestaurantDto();
		dto.setDescription(restaurant.getDescription());
		dto.setImages(restaurant.getImages());
		dto.setTitle(restaurant.getName());
		dto.setId(restaurantId);
		
		//if favourites already exits then it will not add into the favourites
//		if(user.getFavourites().contains(dto)) {
//			user.getFavourites().remove(dto);
//		}
//		else user.getFavourites().add(dto);
			
		boolean isFavourite=false;
		List<RestaurantDto> favorites=user.getFavourites();
		for(RestaurantDto fav:favorites) {
			if(fav.getId().equals(restaurantId)) {
				isFavourite=true;
				break;
			}
		}
		//if the restaurant is in favourite list then it is will be removed or it will add to the favorite list
		if(isFavourite) {
			favorites.removeIf(fav ->fav.getId().equals(restaurantId));
		}else {
			favorites.add(dto);
		}
		userRepository.save(user);
		
		
		
		return dto;
	}

	@Override
	public Restaurant updateRestaurantStatus(Long id) throws Exception {
		// TODO Auto-generated method stub
		Restaurant restaurant=findRestaurantById(id);
		restaurant.setOpen(!restaurant.isOpen());
		return restaurantRepository.save(restaurant);
	}

}
