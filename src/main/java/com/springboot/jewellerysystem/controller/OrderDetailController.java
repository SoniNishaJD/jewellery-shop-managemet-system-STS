package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.OrderDetail; 
import com.springboot.jewellerysystem.service.OrderDetailService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "orderDetail") 
public class OrderDetailController { 
 private OrderDetailService orderDetailService; 
    public OrderDetailController(OrderDetailService orderDetailService) { 
        this.orderDetailService = orderDetailService; 
    }
 
    @GetMapping(value = "/index") 
    public String orderDetails(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<OrderDetail> orderDetails = orderDetailService.getAllOrderDetail(); 
        model.addAttribute("listOrderDetails", orderDetails); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/orderDetails_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formOrderDetails(Model model) { 
        model.addAttribute("orderDetail", new OrderDetail()); 
        return "admin/entry/orderDetail_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteOrderDetail(Integer id, String keyword) { 
        orderDetailService.removeOrderDetail(id); 
        return "redirect:/orderDetails/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateOrderDetail(Model model, Integer id) { 
        OrderDetail orderDetail = orderDetailService.loadOrderDetailById(id); 
        model.addAttribute("OrderDetail", orderDetail); 
        return "admin/edit/OrderDetail_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(OrderDetail orderDetail) { 
        orderDetailService.createOrUpdateOrderDetail(orderDetail); 
        return "redirect:/orderDetails/index"; 
    }
 
} 
