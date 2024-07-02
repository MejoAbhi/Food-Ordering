package com.abhishek.service;

import com.abhishek.entity.Cart;
import com.abhishek.entity.CartItems;
import com.abhishek.request.AddCartItemRequest;

public interface CartService {

	public CartItems addItemToCart(AddCartItemRequest req,String jwt)throws Exception;
	
	public CartItems updateCartItemQuantity(Long cartItemId, int quantity)throws Exception;
	
	public Cart removeItemFromCart(Long cartItemId,String jwt)throws Exception;
	public Long calculateCartTotals(Cart cart)throws Exception;
	public Cart findCartById(Long id)throws Exception;
	public Cart findCartByUserId(Long userId)throws Exception;
	public Cart clearCart(Long userId)throws Exception;
}
