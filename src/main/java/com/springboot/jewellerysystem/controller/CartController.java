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

import com.springboot.jewellerysystem.entity.Cart;
import com.springboot.jewellerysystem.entity.Product;
import com.springboot.jewellerysystem.entity.User;
import com.springboot.jewellerysystem.service.CartService;
import com.springboot.jewellerysystem.service.ProductService;
import com.springboot.jewellerysystem.service.UserService;
import com.springboot.jewellerysystem.util.Helper;

@Controller
@RequestMapping(value = "admin/cart")
public class CartController {
	private CartService cartService;
	private UserService userService;
	private ProductService productService;

	public CartController(CartService cartService, UserService userService, ProductService productService) {
		this.cartService = cartService;
		this.userService = userService;
		this.productService = productService;
	}

	@GetMapping(value = "/index")
	public String carts(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		List<Cart> carts = cartService.getAllCart();
		model.addAttribute("listCarts", carts);
		model.addAttribute("keyword", keyword);
		return "admin/list/carts_list";
	}

	@GetMapping(value = "/create")
	public String formCarts(Model model) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		model.addAttribute("cart", new Cart());
		List<User> users = userService.getAllUser();
		model.addAttribute("listUsers", users);

		List<Product> products = productService.getAllProduct();
		model.addAttribute("listProducts", products);

		return "admin/entry/cart_entry";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteCart(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		cartService.removeCart(id);
		session.setAttribute("msg", "deleted");
		return "redirect:/admin/cart/index?keyword=" + keyword;
	}

	@GetMapping(value = "/update/{id}")
	public String updateCart(@PathVariable(value = "id") Integer id, Model model) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		Cart cart = cartService.loadCartById(id);
		model.addAttribute("cart", cart);
		List<User> users = userService.getAllUser();
		model.addAttribute("listUsers", users);

		List<Product> products = productService.getAllProduct();
		model.addAttribute("listProducts", products);

		return "admin/edit/cart_edit";
	}

	@PostMapping(value = "/save")
	public String save(Cart cart, HttpSession session) {
		String msg = "inserted";
		if(cart.getId() != null && cart.getId() != 0) {
			msg = "updated";
		}
		Cart c =cartService.createOrUpdateCart(cart);
		if(c != null) {
			session.setAttribute("msg", "inserted");
		}else {
			session.setAttribute("error","error");
		}
		
		return "redirect:/admin/cart/index";
	}

}
