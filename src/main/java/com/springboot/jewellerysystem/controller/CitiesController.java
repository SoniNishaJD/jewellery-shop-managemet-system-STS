package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Cities; 
import com.springboot.jewellerysystem.service.CitiesService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "cities") 
public class CitiesController { 
 private CitiesService citiesService; 
    public CitiesController(CitiesService citiesService) { 
        this.citiesService = citiesService; 
    }
 
    @GetMapping(value = "/index") 
    public String citieses(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<Cities> citieses = citiesService.getAllCities(); 
        model.addAttribute("listCitieses", citieses); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/citieses_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formCitieses(Model model) { 
        model.addAttribute("cities", new Cities()); 
        return "admin/entry/cities_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteCities(Integer id, String keyword) { 
        citiesService.removeCities(id); 
        return "redirect:/citieses/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateCities(Model model, Integer id) { 
        Cities cities = citiesService.loadCitiesById(id); 
        model.addAttribute("Cities", cities); 
        return "admin/edit/Cities_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Cities cities) { 
        citiesService.createOrUpdateCities(cities); 
        return "redirect:/citieses/index"; 
    }
 
} 
