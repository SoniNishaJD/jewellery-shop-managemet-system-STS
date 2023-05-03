package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Newsletter; 
import com.springboot.jewellerysystem.service.NewsletterService;
import com.springboot.jewellerysystem.util.Helper;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List;

import javax.servlet.http.HttpSession; 
@Controller 
@RequestMapping(value = "admin/newsletter") 
public class NewsletterController { 
 private NewsletterService newsletterService; 
    public NewsletterController(NewsletterService newsletterService) { 
        this.newsletterService = newsletterService; 
    }
 
    @GetMapping(value = "/index") 
    public String newsletters(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	List<Newsletter> newsletters = newsletterService.getAllNewsletter(); 
        model.addAttribute("listNewsletters", newsletters); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/newsletters_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formNewsletters(Model model) { 
	  if(Helper.checkUserRole()) { return "redirect:/";}
  	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}  
	  model.addAttribute("newsletter", new Newsletter()); 
        return "admin/entry/newsletter_entry"; 
    } 
    @GetMapping(value = "/delete/{id}") 
    public String deleteNewsletter(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	newsletterService.removeNewsletter(id); 
        session.setAttribute("msg", "deleted");
        return "redirect:/admin/newsletter/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update/{id}") 
    public String updateNewsletter(@PathVariable(value = "id") Integer id, Model model) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	Newsletter newsletter = newsletterService.loadNewsletterById(id); 
        model.addAttribute("newsletter", newsletter); 
        return "admin/edit/newsletter_edit"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Newsletter newsletter, HttpSession session) { 
    	String msg = "inserted";
		if(newsletter.getId() != null && newsletter.getId() != 0) {
			msg = "updated";
		}
		Newsletter n = newsletterService.createOrUpdateNewsletter(newsletter); 
		if(n != null) {
    		session.setAttribute("msg", msg);
    	}else {
    		session.setAttribute("error","error");
    	}
        return "redirect:/admin/newsletter/index"; 
    }
 
} 
