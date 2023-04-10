package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Feedback; 
import com.springboot.jewellerysystem.service.FeedbackService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "feedback") 
public class FeedbackController { 
 private FeedbackService feedbackService; 
    public FeedbackController(FeedbackService feedbackService) { 
        this.feedbackService = feedbackService; 
    }
 
    @GetMapping(value = "/index") 
    public String feedbacks(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<Feedback> feedbacks = feedbackService.getAllFeedback(); 
        model.addAttribute("listFeedbacks", feedbacks); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/feedbacks_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formFeedbacks(Model model) { 
        model.addAttribute("feedback", new Feedback()); 
        return "admin/entry/feedback_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteFeedback(Integer id, String keyword) { 
        feedbackService.removeFeedback(id); 
        return "redirect:/feedbacks/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateFeedback(Model model, Integer id) { 
        Feedback feedback = feedbackService.loadFeedbackById(id); 
        model.addAttribute("Feedback", feedback); 
        return "admin/edit/Feedback_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Feedback feedback) { 
        feedbackService.createOrUpdateFeedback(feedback); 
        return "redirect:/feedbacks/index"; 
    }
 
} 
