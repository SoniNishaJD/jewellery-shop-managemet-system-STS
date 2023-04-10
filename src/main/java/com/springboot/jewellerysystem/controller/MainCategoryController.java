package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.MainCategory; 
import com.springboot.jewellerysystem.service.MainCategoryService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "mainCategory") 
public class MainCategoryController { 
 private MainCategoryService mainCategoryService; 
    public MainCategoryController(MainCategoryService mainCategoryService) { 
        this.mainCategoryService = mainCategoryService; 
    }
 
    @GetMapping(value = "/index") 
    public String mainCategories(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<MainCategory> mainCategories = mainCategoryService.getAllMainCategory(); 
        model.addAttribute("listMainCategories", mainCategories); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/mainCategories_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formMainCategories(Model model) { 
        model.addAttribute("mainCategory", new MainCategory()); 
        return "admin/entry/mainCategory_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteMainCategory(Integer id, String keyword) { 
        mainCategoryService.removeMainCategory(id); 
        return "redirect:/mainCategories/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateMainCategory(Model model, Integer id) { 
        MainCategory mainCategory = mainCategoryService.loadMainCategoryById(id); 
        model.addAttribute("MainCategory", mainCategory); 
        return "admin/edit/MainCategory_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(MainCategory mainCategory) { 
        mainCategoryService.createOrUpdateMainCategory(mainCategory); 
        return "redirect:/mainCategories/index"; 
    }
 
} 
