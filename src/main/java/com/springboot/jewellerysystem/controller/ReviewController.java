package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Review; 
import com.springboot.jewellerysystem.service.ReviewService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "review") 
public class ReviewController { 
 private ReviewService reviewService; 
    public ReviewController(ReviewService reviewService) { 
        this.reviewService = reviewService; 
    }
 
    @GetMapping(value = "/index") 
    public String reviews(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<Review> reviews = reviewService.getAllReview(); 
        model.addAttribute("listReviews", reviews); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/reviews_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formReviews(Model model) { 
        model.addAttribute("review", new Review()); 
        return "admin/entry/review_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteReview(Integer id, String keyword) { 
        reviewService.removeReview(id); 
        return "redirect:/reviews/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateReview(Model model, Integer id) { 
        Review review = reviewService.loadReviewById(id); 
        model.addAttribute("Review", review); 
        return "admin/edit/Review_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Review review) { 
        reviewService.createOrUpdateReview(review); 
        return "redirect:/reviews/index"; 
    }
 
} 
