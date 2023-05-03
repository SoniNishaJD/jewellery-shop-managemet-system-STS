package com.springboot.jewellerysystem.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.jewellerysystem.entity.Order;
import com.springboot.jewellerysystem.entity.OrderDetail;
import com.springboot.jewellerysystem.entity.Product;
import com.springboot.jewellerysystem.service.OrderDetailService;
import com.springboot.jewellerysystem.service.OrderService;
import com.springboot.jewellerysystem.service.ProductService;
import com.springboot.jewellerysystem.util.Helper;

@Controller
@RequestMapping(value = "admin/orderDetail")
public class OrderDetailController {
	private OrderDetailService orderDetailService;
	private ProductService productService;
	private OrderService orderService;

	public OrderDetailController(OrderDetailService orderDetailService, ProductService productService,
			OrderService orderService) {
		this.orderDetailService = orderDetailService;
		this.productService = productService;
		this.orderService = orderService;
	}

	@GetMapping(value = "/index/{id}")
	public String orderDetails(Model model, @PathVariable("id") int id)  {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		List<OrderDetail> orderDetails = orderDetailService.getAllOrderDetailByOrder(new Order(id));
		model.addAttribute("listOrderDetails", orderDetails);
		
		return "admin/list/orderDetails_list";
	}

	@GetMapping(value = "/create")
	public String formOrderDetails(Model model) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		model.addAttribute("orderDetail", new OrderDetail());
		List<Product> products = productService.getAllProduct();
		model.addAttribute("listProducts", products);

		List<Order> orders = orderService.getAllOrder();
		model.addAttribute("listOrders", orders);

		return "admin/entry/orderDetail_entry";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteOrderDetail(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		orderDetailService.removeOrderDetail(id);
		session.setAttribute("msg", "deleted");
		return "redirect:/admin/orderDetail/index?keyword=" + keyword;
	}

	@GetMapping(value = "/update/{id}")
	public String updateOrderDetail(@PathVariable(value = "id") Integer id, Model model) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		OrderDetail orderDetail = orderDetailService.loadOrderDetailById(id);
		model.addAttribute("orderDetail", orderDetail);
		List<Product> products = productService.getAllProduct();
		model.addAttribute("listProducts", products);

		List<Order> orders = orderService.getAllOrder();
		model.addAttribute("listOrders", orders);

		return "admin/edit/orderDetail_edit";
	}

	@PostMapping(value = "/save")
	public String save(OrderDetail orderDetail, HttpSession session) {
		String msg = "inserted";
		if(orderDetail.getId() != null && orderDetail.getId() != 0) {
			msg = "updated";
		}
		OrderDetail o =orderDetailService.createOrUpdateOrderDetail(orderDetail);
		if(o != null) {
    		session.setAttribute("msg", msg);
    	}else {
    		session.setAttribute("error","error");
    	}
		return "redirect:/admin/orderDetail/index";
	}

}
