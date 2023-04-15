package com.springboot.jewellerysystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.jewellerysystem.service.UserService;

@Controller

public class AuthController {

	  private UserService userService;

	    public AuthController(UserService userService) {
	        this.userService = userService;
	    }

	    // handler method to handle login page request
	    @GetMapping("/login")
	    public String loginPage(){
	        return "login";
	    }
}
