package com.abhishek.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	private User customer;
	private Long total;
	
	@OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
	private List<CartItems> items=new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getCustomer() {
		return customer;
	}
	public void setCustomer(User customer) {
		this.customer = customer;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<CartItems> getItems() {
		return items;
	}
	public void setItems(List<CartItems> items) {
		this.items = items;
	}
	public Cart(Long id, User customer, Long total, List<CartItems> items) {
		super();
		this.id = id;
		this.customer = customer;
		this.total = total;
		this.items = items;
	}
	public Cart() {
		super();
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", customer=" + customer + ", total=" + total + ", items=" + items + "]";
	}
	
	

}
