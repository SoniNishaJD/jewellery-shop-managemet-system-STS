package com.springboot.jewellerysystem.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.jewellerysystem.entity.BannerType;
import com.springboot.jewellerysystem.service.BannerTypeService;
import com.springboot.jewellerysystem.util.Helper; 
@Controller 
@RequestMapping(value = "admin/bannerType") 
public class BannerTypeController { 
 private BannerTypeService bannerTypeService; 
    public BannerTypeController(BannerTypeService bannerTypeService) { 
        this.bannerTypeService = bannerTypeService; 
    }
 
    @GetMapping(value = "/index") 
    public String bannerTypes(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    			
    	List<BannerType> bannerTypes = bannerTypeService.getAllBannerType(); 
        model.addAttribute("listBannerTypes", bannerTypes); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/bannerTypes_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formBannerTypes(Model model) { 
	  if(Helper.checkUserRole()) { return "redirect:/";}
	  if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
	  		 
	  model.addAttribute("bannerType", new BannerType()); 
        return "admin/entry/bannerType_entry"; 
    } 
    @GetMapping(value = "/delete/{id}") 
    public String deleteBannerType(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    			
    	bannerTypeService.removeBannerType(id); 
        session.setAttribute("msg", "deleted");
        return "redirect:/admin/bannerType/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update/{id}") 
    public String updateBannerType(@PathVariable(value = "id") Integer id, Model model) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    			
    	BannerType bannerType = bannerTypeService.loadBannerTypeById(id); 
        model.addAttribute("bannerType", bannerType); 
        return "admin/edit/bannerType_edit"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(BannerType bannerType, HttpSession session) { 
    	String msg = "inserted";
		if(bannerType.getId() != null && bannerType.getId() != 0) {
			msg = "updated";
		}
        BannerType b =bannerTypeService.createOrUpdateBannerType(bannerType);
        if(b != null) {
			session.setAttribute("msg", "inserted");
		}else {
			session.setAttribute("error","error");
		}
        return "redirect:/admin/bannerType/index"; 
    }
 
} 
