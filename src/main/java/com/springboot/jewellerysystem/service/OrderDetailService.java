package com.springboot.jewellerysystem.service;

import java.util.List;

import com.springboot.jewellerysystem.entity.Order;
import com.springboot.jewellerysystem.entity.OrderDetail;

public interface OrderDetailService { 

  List<OrderDetail> getAllOrderDetail();
  List<OrderDetail> getAllOrderDetailByOrder(Order order);

OrderDetail loadOrderDetailById(Integer id );

OrderDetail createOrUpdateOrderDetail(OrderDetail orderDetail);

void removeOrderDetail(Integer id);

} 
