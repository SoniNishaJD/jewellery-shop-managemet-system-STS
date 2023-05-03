package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.BlogCategory; 
import com.springboot.jewellerysystem.service.BlogCategoryService;
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
@RequestMapping(value = "admin/blogCategory") 
public class BlogCategoryController { 
 private BlogCategoryService blogCategoryService; 
    public BlogCategoryController(BlogCategoryService blogCategoryService) { 
        this.blogCategoryService = blogCategoryService; 
    }
 
    @GetMapping(value = "/index") 
    public String blogCategories(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	List<BlogCategory> blogCategories = blogCategoryService.getAllBlogCategory(); 
        model.addAttribute("listBlogCategories", blogCategories); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/blogCategories_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formBlogCategories(Model model) { 
      
	  if(Helper.checkUserRole()) { return "redirect:/";}
  	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
	  model.addAttribute("blogCategory", new BlogCategory()); 
        return "admin/entry/blogCategory_entry"; 
    } 
    @GetMapping(value = "/delete/{id}") 
    public String deleteBlogCategory(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	blogCategoryService.removeBlogCategory(id); 
        session.setAttribute("msg", "deleted");
        return "redirect:/admin/blogCategory/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update/{id}") 
    public String updateBlogCategory(@PathVariable(value = "id") Integer id, Model model) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	BlogCategory blogCategory = blogCategoryService.loadBlogCategoryById(id); 
        model.addAttribute("blogCategory", blogCategory); 
        return "admin/edit/blogCategory_edit"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(BlogCategory blogCategory, HttpSession session) { 
    	String msg = "inserted";
		if(blogCategory .getId() != null && blogCategory.getId() != 0) {
			msg = "updated";
		}
       BlogCategory b = blogCategoryService.createOrUpdateBlogCategory(blogCategory); 
        if(b != null) {
			session.setAttribute("msg", "inserted");
		}else {
			session.setAttribute("error","error");
		}
        return "redirect:/admin/blogCategory/index"; 
    }
 
} 
