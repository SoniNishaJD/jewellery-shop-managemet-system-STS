package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Country; 
import com.springboot.jewellerysystem.service.CountryService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "country") 
public class CountryController { 
 private CountryService countryService; 
    public CountryController(CountryService countryService) { 
        this.countryService = countryService; 
    }
 
    @GetMapping(value = "/index") 
    public String countries(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<Country> countries = countryService.getAllCountry(); 
        model.addAttribute("listCountries", countries); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/countries_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formCountries(Model model) { 
        model.addAttribute("country", new Country()); 
        return "admin/entry/country_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteCountry(Integer id, String keyword) { 
        countryService.removeCountry(id); 
        return "redirect:/countries/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateCountry(Model model, Integer id) { 
        Country country = countryService.loadCountryById(id); 
        model.addAttribute("Country", country); 
        return "admin/edit/Country_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Country country) { 
        countryService.createOrUpdateCountry(country); 
        return "redirect:/countries/index"; 
    }
 
} 
