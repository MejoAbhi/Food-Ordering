package com.abhishek.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class CartItems {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JsonIgnore
	private Cart cart;
	
	@ManyToOne
	private Food food;
	private int quantity;
	private List<String> ingrediants;
	private Long totalPrice;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
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
	public Long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}
	public CartItems(Long id, Cart cart, Food food, int quantity, List<String> ingrediants, Long totalPrice) {
		super();
		this.id = id;
		this.cart = cart;
		this.food = food;
		this.quantity = quantity;
		this.ingrediants = ingrediants;
		this.totalPrice = totalPrice;
	}
	public CartItems() {
		super();
	}
	@Override
	public String toString() {
		return "CartItems [id=" + id + ", cart=" + cart + ", food=" + food + ", quantity=" + quantity + ", ingrediants="
				+ ingrediants + ", totalPrice=" + totalPrice + "]";
	}
	
	
	
}
