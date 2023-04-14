package com.springboot.jewellerysystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomeController {

	
	@GetMapping("/")
	public String showHome1() {
		return "index";
	}
	@GetMapping("/home2")
	public String showHome2() {
		return "index2";
	}
	@GetMapping("/home3")
	public String showHome3() {
		return "index3";
	}
	
	@GetMapping("/about")
	public String showAbout() {
		return "about-us";
	}
	
	@GetMapping("/contact")
	public String showContact() {
		return "contact";
	}
	
	@GetMapping("/error")
	public String showError() {
		return "404";
	}
	
	@GetMapping("/cart")
	public String showCart() {
		return "cart";
	}
	
	@GetMapping("/checkout")
	public String showCheckout() {
		return "checkout";
	}
	
	@GetMapping("/compare")
	public String showCompare() {
		return "compare";
	}	
	
	
	@GetMapping("/login-register")
	public String showLoginRegister() {
		return "login-register";
	}
	
	@GetMapping("/my-account")
	public String showMyAccount () {
		return "my-account";
	}
	
	@GetMapping("/single-product")
	public String showSingleProduct () {
		return "single-product";
	}
	
	@GetMapping("/shop")
	public String showShop () {
		return "shop-left-sidebar";
	}
	
	@GetMapping("/wishlist")
	public String showWishlist () {
		return "wishlist";
	}
	
	
}
