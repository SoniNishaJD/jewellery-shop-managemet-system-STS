package com.springboot.jewellerysystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.jewellerysystem.service.OrderService;
import com.springboot.jewellerysystem.service.ProductService;

@Controller
public class DashboardController {
	ProductService  productService;
	OrderService orderService;
	
	public DashboardController(ProductService productService, OrderService orderService) {
		super();
		this.productService = productService;
		this.orderService = orderService;
				
	}

	
	
	@GetMapping(value = "/")
	public String getDashboard(Model m) {
		
		int p_count =  productService.getAllProduct().size();
		int o_count =  orderService.getAllOrder().size();
		
		m.addAttribute("product_count",p_count);
		m.addAttribute("order_count",o_count); 
		return "admin/dashboard";
	}



	
}
