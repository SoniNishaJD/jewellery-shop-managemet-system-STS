package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.BlogCategory; 
import com.springboot.jewellerysystem.service.BlogCategoryService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "blogCategory") 
public class BlogCategoryController { 
 private BlogCategoryService blogCategoryService; 
    public BlogCategoryController(BlogCategoryService blogCategoryService) { 
        this.blogCategoryService = blogCategoryService; 
    }
 
    @GetMapping(value = "/index") 
    public String blogCategories(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<BlogCategory> blogCategories = blogCategoryService.getAllBlogCategory(); 
        model.addAttribute("listBlogCategories", blogCategories); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/blogCategories_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formBlogCategories(Model model) { 
        model.addAttribute("blogCategory", new BlogCategory()); 
        return "admin/entry/blogCategory_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteBlogCategory(Integer id, String keyword) { 
        blogCategoryService.removeBlogCategory(id); 
        return "redirect:/blogCategories/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateBlogCategory(Model model, Integer id) { 
        BlogCategory blogCategory = blogCategoryService.loadBlogCategoryById(id); 
        model.addAttribute("BlogCategory", blogCategory); 
        return "admin/edit/BlogCategory_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(BlogCategory blogCategory) { 
        blogCategoryService.createOrUpdateBlogCategory(blogCategory); 
        return "redirect:/blogCategories/index"; 
    }
 
} 
