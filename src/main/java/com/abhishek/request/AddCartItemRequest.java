package com.abhishek.request;

import java.util.List;

public class AddCartItemRequest {

	private Long foodId;
	private int quantity;
	private List<String> ingrediants;
	public Long getFoodId() {
		return foodId;
	}
	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public List<String> getIngrediants() {
		return ingrediants;
	}
	public void setIngrediants(List<String> ingrediants) {
		this.ingrediants = ingrediants;
	}
	
	
}
