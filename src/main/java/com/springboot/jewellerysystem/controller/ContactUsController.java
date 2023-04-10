package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.ContactUs; 
import com.springboot.jewellerysystem.service.ContactUsService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "contactUs") 
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
        return "admin/entry/contactUs_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteContactUs(Integer id, String keyword) { 
        contactUsService.removeContactUs(id); 
        return "redirect:/contactUses/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateContactUs(Model model, Integer id) { 
        ContactUs contactUs = contactUsService.loadContactUsById(id); 
        model.addAttribute("ContactUs", contactUs); 
        return "admin/edit/ContactUs_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(ContactUs contactUs) { 
        contactUsService.createOrUpdateContactUs(contactUs); 
        return "redirect:/contactUses/index"; 
    }
 
} 
