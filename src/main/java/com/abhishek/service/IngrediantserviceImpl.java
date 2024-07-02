package com.abhishek.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.entity.IngrediantsCatagory;
import com.abhishek.entity.IngrediantsItem;
import com.abhishek.entity.Restaurant;
import com.abhishek.repository.IngrediantItemRepository;
import com.abhishek.repository.IngrediantsCategoryRepository;

@Service
public class IngrediantserviceImpl implements IngrediantsService {
	
	@Autowired
	private IngrediantItemRepository ingrediantItemRepository;
	@Autowired
	private IngrediantsCategoryRepository ingrediantCategoryRepository;
	@Autowired
	private RestaurantService restaurantService;

	@Override
	public IngrediantsCatagory createIngrediantCategory(String name, Long restaurantId) throws Exception {
		// TODO Auto-generated method stub
		
		Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);
		IngrediantsCatagory category=new IngrediantsCatagory();
		category.setRestaurant(restaurant);
		category.setName(name);
		return ingrediantCategoryRepository.save(category);
	}

	@Override
	public IngrediantsCatagory findIngrediantCatagoryById(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<IngrediantsCatagory> opt=ingrediantCategoryRepository.findById(id);
		if(opt.isEmpty()) {
			throw new Exception("Catagory not Found....");
		}
		return opt.get();
	}

	@Override
	public List<IngrediantsCatagory> findIngrediantsCatagoryByRestaurantId(Long id) throws Exception {
		// TODO Auto-generated method stub
		
		restaurantService.findRestaurantById(id);
		return ingrediantCategoryRepository.findByRestaurantId(id);
	}

	@Override
	public IngrediantsItem createIngrediantItem(Long restaurantId, String ingrediantName, Long categoryId)
			throws Exception {
		// TODO Auto-generated method stub
		Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);
		
		IngrediantsCatagory category=findIngrediantCatagoryById(categoryId);
		IngrediantsItem item=new IngrediantsItem();
		item.setName(ingrediantName);
		item.setRestaurant(restaurant);
		item.setCatagory(category);
		
		IngrediantsItem ingrediant=ingrediantItemRepository.save(item);
		category.getIngrediants().add(ingrediant);
		
		
		return ingrediant;
	}

	@Override
	public List<IngrediantsItem> findRestaurantIngrediants(Long restaurantId) {
		// TODO Auto-generated method stub
		
		return ingrediantItemRepository.findByRestaurantId(restaurantId);
	}

	@Override
	public IngrediantsItem updateStock(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<IngrediantsItem> opt=ingrediantItemRepository.findById(id);
		if(opt.isEmpty()) {
			throw new Exception("Ingrediant not found..");
		}
		IngrediantsItem ingrediantsItem=opt.get();
		ingrediantsItem.setInStock(!ingrediantsItem.isInStock());
		return ingrediantItemRepository.save(ingrediantsItem);
	}

}
