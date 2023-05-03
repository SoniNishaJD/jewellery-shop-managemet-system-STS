package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Country; 
import com.springboot.jewellerysystem.service.CountryService;
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
@RequestMapping(value = "admin/country") 
public class CountryController { 
 private CountryService countryService; 
    public CountryController(CountryService countryService) { 
        this.countryService = countryService; 
    }
 
    @GetMapping(value = "/index") 
    public String countries(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	List<Country> countries = countryService.getAllCountry(); 
        model.addAttribute("listCountries", countries); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/countries_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formCountries(Model model) { 
	  if(Helper.checkUserRole()) { return "redirect:/";}
  	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";} 
	  model.addAttribute("country", new Country()); 
        return "admin/entry/country_entry"; 
    } 
    @GetMapping(value = "/delete/{id}") 
    public String deleteCountry(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	countryService.removeCountry(id); 
        session.setAttribute("msg", "deleted");
        return "redirect:/admin/country/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update/{id}") 
    public String updateCountry(@PathVariable(value = "id") Integer id, Model model) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	Country country = countryService.loadCountryById(id); 
        model.addAttribute("country", country); 
        return "admin/edit/country_edit"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Country country, HttpSession session) {
    	String msg = "inserted";
		if(country.getId() != null && country.getId() != 0) {
			msg = "updated";
		}
    	Country c = countryService.createOrUpdateCountry(country); 
        if(c != null) {
			session.setAttribute("msg", msg);
		}else {
			session.setAttribute("error","error");
		}
        return "redirect:/admin/country/index"; 
    }
 
} 
