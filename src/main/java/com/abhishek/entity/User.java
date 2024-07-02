package com.abhishek.entity;

import java.util.ArrayList;
import java.util.List;

import com.abhishek.dto.RestaurantDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String fullName;
	private String email;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	private USER_ROLE role=USER_ROLE.ROLE_CUSTOMER;
	//whenever I fetch the user I dont need the list(jsonIgnre)>>>dont create another table for this customer(mappedBy)
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private List<Order> orders=new ArrayList<>();
	
	@ElementCollection
	private List<RestaurantDto> favourites=new ArrayList<>();
	
	//WhenEver any user add address while ordering it save it into the user object
	//One user have mane addresses(one to many)>>> if user is deleted all data of that user is autometically deleted 
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addresses=new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public USER_ROLE getRole() {
		return role;
	}

	public void setRole(USER_ROLE role) {
		this.role = role;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<RestaurantDto> getFavourites() {
		return favourites;
	}

	public void setFavourites(List<RestaurantDto> favourites) {
		this.favourites = favourites;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public User(Long id, String fullName, String email, String password, USER_ROLE role, List<Order> orders,
			List<RestaurantDto> favourites, List<Address> addresses) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.orders = orders;
		this.favourites = favourites;
		this.addresses = addresses;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullName=" + fullName + ", email=" + email + ", password=" + password + ", role="
				+ role + ", orders=" + orders + ", favourites=" + favourites + ", addresses=" + addresses + "]";
	}
	
	
}
