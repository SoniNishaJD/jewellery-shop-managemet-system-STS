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

import com.springboot.jewellerysystem.entity.Product;
import com.springboot.jewellerysystem.entity.Review;
import com.springboot.jewellerysystem.service.ProductService;
import com.springboot.jewellerysystem.service.ReviewService;
import com.springboot.jewellerysystem.util.Helper;

@Controller
@RequestMapping(value = "admin/review")
public class ReviewController {
	private ReviewService reviewService;
	private ProductService productService;

	public ReviewController(ReviewService reviewService, ProductService productService) {
		this.reviewService = reviewService;
		this.productService = productService;
	}

	@GetMapping(value = "/index")
	public String reviews(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		List<Review> reviews = reviewService.getAllReview();
		model.addAttribute("listReviews", reviews);
		model.addAttribute("keyword", keyword);
		return "admin/list/reviews_list";
	}

	@GetMapping(value = "/create")
	public String formReviews(Model model) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		model.addAttribute("review", new Review());
		List<Product> products = productService.getAllProduct();
		model.addAttribute("listProducts", products);

		return "admin/entry/review_entry";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteReview(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		reviewService.removeReview(id);
		session.setAttribute("msg", "deleted");
		return "redirect:/admin/review/index?keyword=" + keyword;
	}

	@GetMapping(value = "/update/{id}")
	public String updateReview(@PathVariable(value = "id") Integer id, Model model) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		Review review = reviewService.loadReviewById(id);
		model.addAttribute("review", review);
		List<Product> products = productService.getAllProduct();
		model.addAttribute("listProducts", products);

		return "admin/edit/review_edit";
	}

	@PostMapping(value = "/save")
	public String save(Review review, HttpSession session) {
		String msg = "inserted";
		if(review.getId() != null && review.getId() != 0) {
			msg = "updated";
		}
		Review r =reviewService.createOrUpdateReview(review);
		if(r != null) {
			session.setAttribute("msg", "inserted");
		}else {
			session.setAttribute("error","error");
		}
		return "redirect:/admin/review/index";
	}

}
