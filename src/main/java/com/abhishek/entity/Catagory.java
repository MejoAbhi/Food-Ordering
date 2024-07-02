package com.abhishek.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
//@data
public class Catagory {
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@JsonIgnore
	@ManyToOne
	private Restaurant restaurant;
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
	public Catagory(Long id, String name, Restaurant restaurant) {
		super();
		this.id = id;
		this.name = name;
		this.restaurant = restaurant;
	}
	public Catagory() {
		super();
	}
	@Override
	public String toString() {
		return "Catagory [id=" + id + ", name=" + name + ", restaurant=" + restaurant + "]";
	}
	
}
