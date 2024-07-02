package com.abhishek.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.entity.Address;
import com.abhishek.entity.Cart;
import com.abhishek.entity.CartItems;
import com.abhishek.entity.Order;
import com.abhishek.entity.OrderItem;
import com.abhishek.entity.Restaurant;
import com.abhishek.entity.User;
import com.abhishek.repository.AddressRepository;
import com.abhishek.repository.OrderItemRepository;
import com.abhishek.repository.OrderRepository;
import com.abhishek.repository.RestaurantRepository;
import com.abhishek.repository.UserRepository;
import com.abhishek.request.OrderRequest;

@Service
public class OrderServiceImp implements OrderService {

	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private CartService cartService;
	
	@Override
	public Order createOrder(OrderRequest order, User user)throws Exception {
		// TODO Auto-generated method stub
		
		Address shippingAddress=order.getDeliveryAddress();
		
		Address saveAddress=addressRepository.save(shippingAddress);
		//checking if address is already exist
		if(!user.getAddresses().contains(saveAddress)) {
			user.getAddresses().add(saveAddress);
			userRepository.save(user);
			
		}
		
		Restaurant restaurant=restaurantService.findRestaurantById(order.getRestaurantId());
		
		
		
		Order createdOrder=new Order();
		createdOrder.setCustomer(user);
		createdOrder.setCreatedAt(new Date());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.setDeliveryAddress(saveAddress);
		createdOrder.setRestaureant(restaurant);
		
		
		//creating order Item
		Cart cart=cartService.findCartByUserId(user.getId());
		
		
		//Look through all the order Item
		
		List<OrderItem> orderItems=new ArrayList<>();
		for(CartItems cartItems: cart.getItems()) {
			OrderItem item=new OrderItem();
			//create order item
			item.setFood(cartItems.getFood());
			item.setIngrediants(cartItems.getIngrediants());
			item.setQuantity(cartItems.getQuantity());
			item.setTotalPrice(cartItems.getTotalPrice());
			//save order item
			OrderItem saveorderItem=orderItemRepository.save(item);
			orderItems.add(saveorderItem);
		}
		
		
		
		return null;
	}

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelOrder(Long orderId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> getUserOrder(Long userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
