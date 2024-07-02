package com.abhishek.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class IngrediantsItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@ManyToOne
	private IngrediantsCatagory catagory;
	@JsonIgnore
	@ManyToOne
	private Restaurant restaurant;
	
	private boolean inStock=true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IngrediantsCatagory getCatagory() {
		return catagory;
	}

	public void setCatagory(IngrediantsCatagory catagory) {
		this.catagory = catagory;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public IngrediantsItem(Long id, String name, IngrediantsCatagory catagory, Restaurant restaurant, boolean inStock) {
		super();
		this.id = id;
		this.name = name;
		this.catagory = catagory;
		this.restaurant = restaurant;
		this.inStock = inStock;
	}

	public IngrediantsItem() {
		super();
	}

	@Override
	public String toString() {
		return "IngrediantsItem [id=" + id + ", name=" + name + ", catagory=" + catagory + ", restaurant=" + restaurant
				+ ", inStock=" + inStock + "]";
	}
	
	
	
	
}
