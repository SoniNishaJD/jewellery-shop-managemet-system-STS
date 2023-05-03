package com.springboot.jewellerysystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jewellerysystem.entity.Order;

public interface OrderDAO extends JpaRepository<Order, Integer> { 

	
	
} 
