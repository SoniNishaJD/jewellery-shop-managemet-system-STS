package com.springboot.jewellerysystem.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.jewellerysystem.entity.ContactUs;
import com.springboot.jewellerysystem.service.ContactUsService; 
@Controller 
@RequestMapping(value = "admin/contactUs") 
public class ContactUsController { 
 private ContactUsService contactUsService; 
    public ContactUsController(ContactUsService contactUsService) { 
        this.contactUsService = contactUsService; 
    }
 
    @GetMapping(value = "/index") 
    public String contactUses(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<ContactUs> contactUses = contactUsService.getAllContactUs(); 
        model.addAttribute("listContactUses", contactUses); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/contactUses_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formContactUses(Model model) { 
        model.addAttribute("contactUs", new ContactUs()); 
        model.addAttribute("myDate", new Date());
        return "admin/entry/contactUs_entry"; 
    } 
    @GetMapping(value = "/delete/{id}") 
    public String deleteContactUs(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) { 
        contactUsService.removeContactUs(id); 
        session.setAttribute("msg", "deleted");
        return "redirect:/admin/contactUs/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update/{id}") 
    public String updateContactUs(@PathVariable(value = "id") Integer id, Model model) { 
        ContactUs contactUs = contactUsService.loadContactUsById(id); 
        model.addAttribute("contactUs", contactUs); 
        return "admin/edit/contactUs_edit"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(ContactUs contactUs, HttpSession session) { 
    	String msg = "inserted";
		if(contactUs.getId() != null && contactUs.getId() != 0) {
			msg = "updated";
		}
    	if(contactUs.getIsRead() == null)
    	{contactUs.setIsRead("N");}
    	
    	if(contactUs.getEnquiryDate() == null) {
    		contactUs.setEnquiryDate(new Date());
    	}
    	ContactUs c =  contactUsService.createOrUpdateContactUs(contactUs);
        if(c != null) {
			session.setAttribute("msg", "inserted");
		}else {
			session.setAttribute("error","error");
		}
        return "redirect:/admin/contactUs/index"; 
    }
 
} 
