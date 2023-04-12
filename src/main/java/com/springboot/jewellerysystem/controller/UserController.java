package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.User; 
import com.springboot.jewellerysystem.service.UserService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "user") 
public class UserController { 
 private UserService userService; 
    public UserController(UserService userService) { 
        this.userService = userService; 
    }
 
    @GetMapping(value = "/index") 
    public String users(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<User> users = userService.getAllUser(); 
        model.addAttribute("listUsers", users); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/users_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formUsers(Model model) { 
        model.addAttribute("user", new User()); 
        return "admin/entry/user_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteUser(Integer id, String keyword) { 
        userService.removeUser(id); 
        return "redirect:/user/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateUser(Model model, Integer id) { 
        User user = userService.loadUserById(id); 
        model.addAttribute("User", user); 
        return "admin/edit/User_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(User user) { 
        userService.createOrUpdateUser(user); 
        return "redirect:/user/index"; 
    }
 
} 
