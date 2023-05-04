package com.springboot.jewellerysystem.service;

import java.util.List;

import com.springboot.jewellerysystem.entity.Order;
import com.springboot.jewellerysystem.entity.OrderDetail;
import com.springboot.jewellerysystem.entity.User;

public interface OrderDetailService {

	List<OrderDetail> getAllOrderDetail();

	List<OrderDetail> getAllOrderDetailByOrder(Order order);

	OrderDetail loadOrderDetailById(Integer id);

	OrderDetail createOrUpdateOrderDetail(OrderDetail orderDetail);

	void removeOrderDetail(Integer id);
	
	void removeOrderDetaiilByUser(User user);

}
