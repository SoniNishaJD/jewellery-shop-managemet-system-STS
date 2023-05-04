package com.springboot.jewellerysystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jewellerysystem.entity.Cart;
import com.springboot.jewellerysystem.entity.User;

public interface CartDAO extends JpaRepository<Cart, Integer> { 
	List<Cart> findByUser(User user);
	void deleteByUser(User user);

} 
