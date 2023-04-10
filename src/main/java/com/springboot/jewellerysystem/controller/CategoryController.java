package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Category; 
import com.springboot.jewellerysystem.service.CategoryService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "category") 
public class CategoryController { 
 private CategoryService categoryService; 
    public CategoryController(CategoryService categoryService) { 
        this.categoryService = categoryService; 
    }
 
    @GetMapping(value = "/index") 
    public String categories(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<Category> categories = categoryService.getAllCategory(); 
        model.addAttribute("listCategories", categories); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/categories_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formCategories(Model model) { 
        model.addAttribute("category", new Category()); 
        return "admin/entry/category_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteCategory(Integer id, String keyword) { 
        categoryService.removeCategory(id); 
        return "redirect:/categories/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateCategory(Model model, Integer id) { 
        Category category = categoryService.loadCategoryById(id); 
        model.addAttribute("Category", category); 
        return "admin/edit/Category_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Category category) { 
        categoryService.createOrUpdateCategory(category); 
        return "redirect:/categories/index"; 
    }
 
} 
