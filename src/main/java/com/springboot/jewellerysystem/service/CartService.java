package com.springboot.jewellerysystem.service;

import java.util.List;

import com.springboot.jewellerysystem.entity.Cart;
import com.springboot.jewellerysystem.entity.User;

public interface CartService {

	List<Cart> getAllCart();
	List<Cart> getAllCartByUser(User user);

	Cart loadCartById(Integer id);

	Cart createOrUpdateCart(Cart cart);

	void removeCart(Integer id);
	void removeCartByUser(User user);

}
