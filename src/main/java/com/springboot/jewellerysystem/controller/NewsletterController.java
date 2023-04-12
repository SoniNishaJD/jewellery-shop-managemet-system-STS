package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Newsletter; 
import com.springboot.jewellerysystem.service.NewsletterService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "newsletter") 
public class NewsletterController { 
 private NewsletterService newsletterService; 
    public NewsletterController(NewsletterService newsletterService) { 
        this.newsletterService = newsletterService; 
    }
 
    @GetMapping(value = "/index") 
    public String newsletters(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<Newsletter> newsletters = newsletterService.getAllNewsletter(); 
        model.addAttribute("listNewsletters", newsletters); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/newsletters_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formNewsletters(Model model) { 
        model.addAttribute("newsletter", new Newsletter()); 
        return "admin/entry/newsletter_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteNewsletter(Integer id, String keyword) { 
        newsletterService.removeNewsletter(id); 
        return "redirect:/newsletter/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateNewsletter(Model model, Integer id) { 
        Newsletter newsletter = newsletterService.loadNewsletterById(id); 
        model.addAttribute("Newsletter", newsletter); 
        return "admin/edit/Newsletter_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Newsletter newsletter) { 
        newsletterService.createOrUpdateNewsletter(newsletter); 
        return "redirect:/newsletter/index"; 
    }
 
} 