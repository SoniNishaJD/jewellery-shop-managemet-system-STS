package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Faq; 
import com.springboot.jewellerysystem.service.FaqService;
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
@RequestMapping(value = "admin/faq") 
public class FaqController { 
 private FaqService faqService; 
    public FaqController(FaqService faqService) { 
        this.faqService = faqService; 
    }
 
    @GetMapping(value = "/index") 
    public String faqs(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	List<Faq> faqs = faqService.getAllFaq(); 
        model.addAttribute("listFaqs", faqs); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/faqs_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formFaqs(Model model) { 
	  if(Helper.checkUserRole()) { return "redirect:/";}
  	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";} 
	  model.addAttribute("faq", new Faq()); 
        return "admin/entry/faq_entry"; 
    } 
    @GetMapping(value = "/delete/{id}") 
    public String deleteFaq(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	faqService.removeFaq(id); 
        session.setAttribute("msg", "deleted");
        return "redirect:/admin/faq/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update/{id}") 
    public String updateFaq(@PathVariable(value = "id") Integer id, Model model) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	Faq faq = faqService.loadFaqById(id); 
        model.addAttribute("faq", faq); 
        return "admin/edit/faq_edit"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Faq faq, HttpSession session) { 
    	String msg = "inserted";
		if(faq.getId() != null && faq.getId() != 0) {
			msg = "updated";
		}
       Faq f =  faqService.createOrUpdateFaq(faq); 
   	if(f != null) {
		session.setAttribute("msg", msg);
	}else {
		session.setAttribute("error","error");
	}
        return "redirect:/admin/faq/index"; 
    }
 
} 
