package com.springboot.jewellerysystem.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.jewellerysystem.entity.Product;
import com.springboot.jewellerysystem.entity.User;
import com.springboot.jewellerysystem.entity.Wishlist;
import com.springboot.jewellerysystem.service.ProductService;
import com.springboot.jewellerysystem.service.UserService;
import com.springboot.jewellerysystem.service.WishlistService;

@Controller
@RequestMapping(value = "wishlist")
public class WishlistController {
	private WishlistService wishlistService;
	private ProductService productService;
	private UserService userService;

	public WishlistController(WishlistService wishlistService, ProductService productService, UserService userService) {
		this.wishlistService = wishlistService;
		this.productService = productService;
		this.userService = userService;
	}

	@GetMapping(value = "/index")
	public String wishlists(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
		List<Wishlist> wishlists = wishlistService.getAllWishlist();
		model.addAttribute("listWishlists", wishlists);
		model.addAttribute("keyword", keyword);
		return "admin/list/wishlists_list";
	}

	@GetMapping(value = "/create")
	public String formWishlists(Model model) {
		model.addAttribute("wishlist", new Wishlist());
		List<Product> products = productService.getAllProduct();
		model.addAttribute("listProducts", products);

		List<User> users = userService.getAllUser();
		model.addAttribute("listUsers", users);

		return "admin/entry/wishlist_entry";
	}

	@GetMapping(value = "/delete")
	public String deleteWishlist(Integer id, String keyword) {
		wishlistService.removeWishlist(id);
		return "redirect:/wishlists/index?keyword=" + keyword;
	}

	@GetMapping(value = "/update")
	public String updateWishlist(Model model, Integer id) {
		Wishlist wishlist = wishlistService.loadWishlistById(id);
		model.addAttribute("Wishlist", wishlist);
		List<Product> products = productService.getAllProduct();
		model.addAttribute("listProducts", products);

		List<User> users = userService.getAllUser();
		model.addAttribute("listUsers", users);

		return "admin/edit/Wishlist_update";
	}

	@PostMapping(value = "/save")
	public String save(Wishlist wishlist) {
		wishlistService.createOrUpdateWishlist(wishlist);
		return "redirect:/wishlists/index";
	}

}
