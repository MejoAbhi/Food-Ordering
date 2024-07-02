package com.abhishek.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.entity.Catagory;
import com.abhishek.entity.Restaurant;
import com.abhishek.repository.CategoryRepository;


@Service
public class CategoryServiceImp implements CategoryService{
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Catagory createCategory(String name, Long userId) throws Exception {
		// TODO Auto-generated method stub
		Restaurant restaurant=restaurantService.geteRestaurantByUserId(userId);
		Catagory category=new Catagory();
		category.setName(name);
		category.setRestaurant(restaurant);
		return categoryRepository.save(category);
	}

	@Override
	public List<Catagory> findCategoryByRestaurantId(Long id) throws Exception {
		// TODO Auto-generated method stub
		Restaurant restaurant=restaurantService.geteRestaurantByUserId(id);
		return categoryRepository.findByRestaurantId(restaurant.getId());
	}

	@Override
	public Catagory findCategoryById(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<Catagory> optionCategory=categoryRepository.findById(id);
		if(optionCategory.isEmpty()) {
			throw new Exception("Category not Found....");
		}
		return optionCategory.get();
	}

}
