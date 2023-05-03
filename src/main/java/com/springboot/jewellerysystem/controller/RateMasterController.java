package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.RateMaster; 
import com.springboot.jewellerysystem.service.RateMasterService;
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
@RequestMapping(value = "admin/rateMaster") 
public class RateMasterController { 
 private RateMasterService rateMasterService; 
    public RateMasterController(RateMasterService rateMasterService) { 
        this.rateMasterService = rateMasterService; 
    }
 
    @GetMapping(value = "/index") 
    public String rateMasters(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	List<RateMaster> rateMasters = rateMasterService.getAllRateMaster(); 
        model.addAttribute("listRateMasters", rateMasters); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/rateMasters_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formRateMasters(Model model) { 
        
	  if(Helper.checkUserRole()) { return "redirect:/";}
  	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
  	model.addAttribute("rateMaster", new RateMaster()); 
        return "admin/entry/rateMaster_entry"; 
    } 
    @GetMapping(value = "/delete/{id}") 
    public String deleteRateMaster(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	rateMasterService.removeRateMaster(id); 
        session.setAttribute("msg", "deleted");
        return "redirect:/admin/rateMaster/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update/{id}") 
    public String updateRateMaster(@PathVariable(value = "id") Integer id, Model model) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	RateMaster rateMaster = rateMasterService.loadRateMasterById(id); 
        model.addAttribute("rateMaster", rateMaster); 
        return "admin/edit/rateMaster_edit"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(RateMaster rateMaster, HttpSession session) {
    	String msg = "inserted";
		if(rateMaster.getId() != null && rateMaster.getId() != 0) {
			msg = "updated";
		}
    	RateMaster r = rateMasterService.createOrUpdateRateMaster(rateMaster); 
        if(r != null) {
			session.setAttribute("msg", "inserted");
		}else {
			session.setAttribute("error","error");
		}

        return "redirect:/admin/rateMaster/index"; 
    }
 
} 
