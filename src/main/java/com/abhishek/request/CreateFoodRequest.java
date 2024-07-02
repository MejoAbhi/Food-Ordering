package com.abhishek.request;

import java.util.List;

import com.abhishek.entity.Catagory;
import com.abhishek.entity.IngrediantsItem;

public class CreateFoodRequest {

	private String name;
	private String description;
	private Long price;
	
	private Catagory catagory;
	private List<String> images;
	
	private Long restaurantId;
	private boolean vegetarin;
	private boolean seasional;
	private List<IngrediantsItem> ingrediants;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Catagory getCatagory() {
		return catagory;
	}
	public void setCatagory(Catagory catagory) {
		this.catagory = catagory;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public Long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}
	public boolean isVegetarin() {
		return vegetarin;
	}
	public void setVegetarin(boolean vegetarin) {
		this.vegetarin = vegetarin;
	}
	public boolean isSeasional() {
		return seasional;
	}
	public void setSeasional(boolean seasional) {
		this.seasional = seasional;
	}
	public List<IngrediantsItem> getIngrediants() {
		return ingrediants;
	}
	public void setIngrediants(List<IngrediantsItem> ingrediants) {
		this.ingrediants = ingrediants;
	}
	
	
	
	
}
