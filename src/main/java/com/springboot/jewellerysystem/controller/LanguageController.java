package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Language; 
import com.springboot.jewellerysystem.service.LanguageService;
import com.springboot.jewellerysystem.util.Helper;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession; 
@Controller 
@RequestMapping(value = "admin/language") 
public class LanguageController { 
 private LanguageService languageService; 
    public LanguageController(LanguageService languageService) { 
        this.languageService = languageService; 
    }
 
    @GetMapping(value = "/index") 
    public String languages(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	List<Language> languages = languageService.getAllLanguage(); 
        model.addAttribute("listLanguages", languages); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/languages_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formLanguages(Model model) { 
	  if(Helper.checkUserRole()) { return "redirect:/";}
  	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}  
	  model.addAttribute("language", new Language()); 
        return "admin/entry/language_entry"; 
    } 
    @GetMapping(value = "/delete/{id}") 
    public String deleteLanguage(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	languageService.removeLanguage(id); 
        session.setAttribute("msg", "deleted");
        return "redirect:/admin/language/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update/{id}") 
    public String updateLanguage(@PathVariable(value = "id") Integer id, Model model) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	Language language = languageService.loadLanguageById(id); 
        model.addAttribute("language", language); 
        return "admin/edit/language_edit"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Language language, HttpSession session) { 
    	String msg = "inserted";
		if(language.getId() != null && language.getId() != 0) {
			msg = "updated";
		}
    	
      Language l =  languageService.createOrUpdateLanguage(language); 
        if(l != null) {
    		session.setAttribute("msg", msg);
    	}else {
    		session.setAttribute("error","error");
    	}
        return "redirect:/admin/language/index"; 
    }
 
} 
