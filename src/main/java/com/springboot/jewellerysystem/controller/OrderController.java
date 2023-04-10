package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Order; 
import com.springboot.jewellerysystem.service.OrderService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "order") 
public class OrderController { 
 private OrderService orderService; 
    public OrderController(OrderService orderService) { 
        this.orderService = orderService; 
    }
 
    @GetMapping(value = "/index") 
    public String orders(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<Order> orders = orderService.getAllOrder(); 
        model.addAttribute("listOrders", orders); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/orders_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formOrders(Model model) { 
        model.addAttribute("order", new Order()); 
        return "admin/entry/order_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteOrder(Integer id, String keyword) { 
        orderService.removeOrder(id); 
        return "redirect:/orders/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateOrder(Model model, Integer id) { 
        Order order = orderService.loadOrderById(id); 
        model.addAttribute("Order", order); 
        return "admin/edit/Order_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Order order) { 
        orderService.createOrUpdateOrder(order); 
        return "redirect:/orders/index"; 
    }
 
} 
