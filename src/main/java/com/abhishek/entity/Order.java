package com.abhishek.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	//Many order has same user
	@ManyToOne
	private User customer;
	@JsonIgnore
	//many order has same user
	@ManyToOne
	private Restaurant restaureant;
	private Long totalAmount;
	private String orderStatus;
	private Date createdAt;
	@ManyToOne
	private Address deliveryAddress;
	//inside one order user has many items
	@OneToMany
	private List<OrderItem> items;
	//private Payment payment;
	private int totalItrem;
	private int totalprice;
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
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


	public Restaurant getRestaureant() {
		return restaureant;
	}


	public void setRestaureant(Restaurant restaureant) {
		this.restaureant = restaureant;
	}


	public Long getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}


	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Address getDeliveryAddress() {
		return deliveryAddress;
	}


	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}


	public List<OrderItem> getItems() {
		return items;
	}


	public void setItems(List<OrderItem> items) {
		this.items = items;
	}


	public int getTotalItrem() {
		return totalItrem;
	}


	public void setTotalItrem(int totalItrem) {
		this.totalItrem = totalItrem;
	}


	public int getTotalprice() {
		return totalprice;
	}


	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}


	public Order() {
		super();
	}


	public Order(Long id, User customer, Restaurant restaureant, Long totalAmount, String orderStatus, Date createdAt,
			Address deliveryAddress, List<OrderItem> items, int totalItrem, int totalprice) {
		super();
		this.id = id;
		this.customer = customer;
		this.restaureant = restaureant;
		this.totalAmount = totalAmount;
		this.orderStatus = orderStatus;
		this.createdAt = createdAt;
		this.deliveryAddress = deliveryAddress;
		this.items = items;
		this.totalItrem = totalItrem;
		this.totalprice = totalprice;
	}


	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", restaureant=" + restaureant + ", totalAmount="
				+ totalAmount + ", orderStatus=" + orderStatus + ", createdAt=" + createdAt + ", deliveryAddress="
				+ deliveryAddress + ", items=" + items + ", totalItrem=" + totalItrem + ", totalprice=" + totalprice
				+ "]";
	}
	
	
}
