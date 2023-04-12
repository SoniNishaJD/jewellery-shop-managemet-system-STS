package com.springboot.jewellerysystem.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.jewellerysystem.entity.Cart;
import com.springboot.jewellerysystem.entity.Product;
import com.springboot.jewellerysystem.entity.User;
import com.springboot.jewellerysystem.service.CartService;
import com.springboot.jewellerysystem.service.ProductService;
import com.springboot.jewellerysystem.service.UserService;

@Controller
@RequestMapping(value = "cart")
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
		List<Cart> carts = cartService.getAllCart();
		model.addAttribute("listCarts", carts);
		model.addAttribute("keyword", keyword);
		return "admin/list/carts_list";
	}

	@GetMapping(value = "/create")
	public String formCarts(Model model) {
		model.addAttribute("cart", new Cart());
		List<User> users = userService.getAllUser();
		model.addAttribute("listUsers", users);

		List<Product> products = productService.getAllProduct();
		model.addAttribute("listProducts", products);

		return "admin/entry/cart_entry";
	}

	@GetMapping(value = "/delete")
	public String deleteCart(Integer id, String keyword) {
		cartService.removeCart(id);
		return "redirect:/cart/index?keyword=" + keyword;
	}

	@GetMapping(value = "/update")
	public String updateCart(Model model, Integer id) {
		Cart cart = cartService.loadCartById(id);
		model.addAttribute("Cart", cart);
		List<User> users = userService.getAllUser();
		model.addAttribute("listUsers", users);

		List<Product> products = productService.getAllProduct();
		model.addAttribute("listProducts", products);

		return "admin/edit/Cart_update";
	}

	@PostMapping(value = "/save")
	public String save(Cart cart) {
		cartService.createOrUpdateCart(cart);
		return "redirect:/cart/index";
	}

}
