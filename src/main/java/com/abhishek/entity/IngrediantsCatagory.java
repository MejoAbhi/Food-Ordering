package com.abhishek.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
//@data
public class IngrediantsCatagory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	
	@JsonIgnore
	@ManyToOne
	private Restaurant restaurant;
	@OneToMany(mappedBy = "catagory",cascade = CascadeType.ALL)
	private List<IngrediantsItem> ingrediants= new ArrayList<>();
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
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public List<IngrediantsItem> getIngrediants() {
		return ingrediants;
	}
	public void setIngrediants(List<IngrediantsItem> ingrediants) {
		this.ingrediants = ingrediants;
	}
	public IngrediantsCatagory(Long id, String name, Restaurant restaurant, List<IngrediantsItem> ingrediants) {
		super();
		this.id = id;
		this.name = name;
		this.restaurant = restaurant;
		this.ingrediants = ingrediants;
	}
	public IngrediantsCatagory() {
		super();
	}
	@Override
	public String toString() {
		return "IngrediantsCatagory [id=" + id + ", name=" + name + ", restaurant=" + restaurant + ", ingrediants="
				+ ingrediants + "]";
	}
	
	
	
}
