package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Blog; 
import com.springboot.jewellerysystem.service.BlogService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "blog") 
public class BlogController { 
 private BlogService blogService; 
    public BlogController(BlogService blogService) { 
        this.blogService = blogService; 
    }
 
    @GetMapping(value = "/index") 
    public String blogs(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<Blog> blogs = blogService.getAllBlog(); 
        model.addAttribute("listBlogs", blogs); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/blogs_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formBlogs(Model model) { 
        model.addAttribute("blog", new Blog()); 
        return "admin/entry/blog_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteBlog(Integer id, String keyword) { 
        blogService.removeBlog(id); 
        return "redirect:/blogs/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateBlog(Model model, Integer id) { 
        Blog blog = blogService.loadBlogById(id); 
        model.addAttribute("Blog", blog); 
        return "admin/edit/Blog_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Blog blog) { 
        blogService.createOrUpdateBlog(blog); 
        return "redirect:/blogs/index"; 
    }
 
} 
