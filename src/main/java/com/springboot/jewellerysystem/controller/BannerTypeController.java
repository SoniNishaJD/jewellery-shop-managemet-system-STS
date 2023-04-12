package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.BannerType; 
import com.springboot.jewellerysystem.service.BannerTypeService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "bannerType") 
public class BannerTypeController { 
 private BannerTypeService bannerTypeService; 
    public BannerTypeController(BannerTypeService bannerTypeService) { 
        this.bannerTypeService = bannerTypeService; 
    }
 
    @GetMapping(value = "/index") 
    public String bannerTypes(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<BannerType> bannerTypes = bannerTypeService.getAllBannerType(); 
        model.addAttribute("listBannerTypes", bannerTypes); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/bannerTypes_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formBannerTypes(Model model) { 
        model.addAttribute("bannerType", new BannerType()); 
        return "admin/entry/bannerType_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteBannerType(Integer id, String keyword) { 
        bannerTypeService.removeBannerType(id); 
        return "redirect:/bannerType/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateBannerType(Model model, Integer id) { 
        BannerType bannerType = bannerTypeService.loadBannerTypeById(id); 
        model.addAttribute("BannerType", bannerType); 
        return "admin/edit/BannerType_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(BannerType bannerType) { 
        bannerTypeService.createOrUpdateBannerType(bannerType); 
        return "redirect:/bannerType/index"; 
    }
 
} 
