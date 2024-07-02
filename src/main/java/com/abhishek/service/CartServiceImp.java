package com.abhishek.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.entity.Cart;
import com.abhishek.entity.CartItems;
import com.abhishek.entity.Food;
import com.abhishek.entity.User;
import com.abhishek.repository.CartRepository;
import com.abhishek.repository.CartitemRepository;
import com.abhishek.repository.FoodRepository;
import com.abhishek.request.AddCartItemRequest;

@Service
public class CartServiceImp implements CartService {

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartitemRepository cartitemRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FoodService foodService;
	
	
	@Override
	public CartItems addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
		// TODO Auto-generated method stub
		//Findingusers because in which user we want to add the cart
		User user=userService.findUSerByJwtToken(jwt);
		
		Food food=foodService.findFoodById(req.getFoodId());
		Cart cart=cartRepository.findByCustomerId(user.getId());
		
		//cart Logic
		for(CartItems cartItems : cart.getItems()) {
			if(cartItems.getFood().equals(food)) {
				int newQuantity=cartItems.getQuantity()+req.getQuantity();
				return updateCartItemQuantity(cartItems.getId(), newQuantity);
			}
		}
		CartItems newcartItems=new CartItems();
		newcartItems.setFood(food);
		newcartItems.setCart(cart);
		newcartItems.setQuantity(req.getQuantity());
		newcartItems.setIngrediants(req.getIngrediants());
		newcartItems.setTotalPrice(req.getQuantity()*food.getPrice());
		CartItems saveCartItem=cartitemRepository.save(newcartItems);
		
		cart.getItems().add(saveCartItem);
		return saveCartItem;
	}

	@Override
	public CartItems updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
		// TODO Auto-generated method stub
		Optional<CartItems> cartItemsOptional=cartitemRepository.findById(cartItemId);
		if(cartItemsOptional.isEmpty()) {
			throw new Exception("Cart Item Not Found");
		}
		CartItems item=cartItemsOptional.get();
		item.setQuantity(quantity);
		item.setTotalPrice(item.getFood().getPrice()*quantity);
		return cartitemRepository.save(item);
	}

	@Override
	public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
		// TODO Auto-generated method stub
		User user=userService.findUSerByJwtToken(jwt);
		
		
		Cart cart=cartRepository.findByCustomerId(user.getId());
		Optional<CartItems> cartItemsOptional=cartitemRepository.findById(cartItemId);
		if(cartItemsOptional.isEmpty()) {
			throw new Exception("Cart Item Not Found");
		}
		CartItems item=cartItemsOptional.get();
		cart.getItems().remove(item);
		
		return cartRepository.save(cart);
	}

	@Override
	public Long calculateCartTotals(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		
		Long total=0L;
		for(CartItems cartItems :cart.getItems()) {
			total+=cartItems.getFood().getPrice()*cartItems.getQuantity();
		}
		return total;
	}

	@Override
	public Cart findCartById(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<Cart> optionalCart=cartRepository.findById(id);
		if(optionalCart.isEmpty()) {
			throw new Exception("Cart not found with Id..."+id);
		}
		
		return optionalCart.get();
	}

	@Override
	public Cart findCartByUserId(Long userId) throws Exception {
		// TODO Auto-generated method stub
		//User user=userService.findUSerByJwtToken(jwt);
		return cartRepository.findByCustomerId(userId);
	}

	@Override
	public Cart clearCart(Long userId) throws Exception {
		// TODO Auto-generated method stub
		//User user=userService.findUSerByJwtToken(jwt);
		Cart cart=findCartByUserId(userId);
		cart.getItems().clear();
		return cartRepository.save(cart);
	}

}
