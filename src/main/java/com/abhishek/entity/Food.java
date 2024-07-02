package com.abhishek.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
@Entity

public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	private Long price;
	@ManyToOne
	private Catagory foodCatagory;
	
	@Column(length = 1000)
	//for element collection it will create seperate table for images
	@ElementCollection
	private List<String> images;
	private boolean available;
	//mustiple food has same resturent
	@ManyToOne
	private Restaurant restaureant;
	private boolean isVeg;
	private boolean isSeasonal;
	@ManyToMany
	private List<IngrediantsItem> ingrediants=new ArrayList<>();
	private Date creationDate;
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
	public Catagory getFoodCatagory() {
		return foodCatagory;
	}
	public void setFoodCatagory(Catagory foodCatagory) {
		this.foodCatagory = foodCatagory;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public Restaurant getRestaureant() {
		return restaureant;
	}
	public void setRestaureant(Restaurant restaureant) {
		this.restaureant = restaureant;
	}
	public boolean isVeg() {
		return isVeg;
	}
	public void setVeg(boolean isVeg) {
		this.isVeg = isVeg;
	}
	public boolean isSeasonal() {
		return isSeasonal;
	}
	public void setSeasonal(boolean isSeasonal) {
		this.isSeasonal = isSeasonal;
	}
	public List<IngrediantsItem> getIngrediants() {
		return ingrediants;
	}
	public void setIngrediants(List<IngrediantsItem> ingrediants) {
		this.ingrediants = ingrediants;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Food(Long id, String name, String description, Long price, Catagory foodCatagory, List<String> images,
			boolean available, Restaurant restaureant, boolean isVeg, boolean isSeasonal,
			List<IngrediantsItem> ingrediants, Date creationDate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.foodCatagory = foodCatagory;
		this.images = images;
		this.available = available;
		this.restaureant = restaureant;
		this.isVeg = isVeg;
		this.isSeasonal = isSeasonal;
		this.ingrediants = ingrediants;
		this.creationDate = creationDate;
	}
	public Food() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", foodCatagory=" + foodCatagory + ", images=" + images + ", available=" + available
				+ ", restaureant=" + restaureant + ", isVeg=" + isVeg + ", isSeasonal=" + isSeasonal + ", ingrediants="
				+ ingrediants + ", creationDate=" + creationDate + "]";
	}
	
	
	
}

