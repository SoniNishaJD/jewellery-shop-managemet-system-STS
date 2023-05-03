package com.springboot.jewellerysystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jewellerysystem.entity.Order;
import com.springboot.jewellerysystem.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Integer> { 
	
	List<OrderDetail>  findAllOrderDetailByOrder(Order order);

} 
