package com.abhishek.service;

import java.util.List;

import com.abhishek.entity.Order;
import com.abhishek.entity.User;
import com.abhishek.request.OrderRequest;

public interface OrderService {

	public Order createOrder(OrderRequest order, User user)throws Exception;
	
	public Order updateOrder(Long orderId,String orderStatus) throws Exception;
	
	public void cancelOrder(Long orderId)throws Exception;
	
	public List<Order> getUserOrder(Long userId)throws Exception;
	//pending/Done Etc
	public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus)throws Exception;
}
