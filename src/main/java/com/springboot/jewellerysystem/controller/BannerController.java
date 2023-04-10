package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Banner; 
import com.springboot.jewellerysystem.service.BannerService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "banner") 
public class BannerController { 
 private BannerService bannerService; 
    public BannerController(BannerService bannerService) { 
        this.bannerService = bannerService; 
    }
 
    @GetMapping(value = "/index") 
    public String banners(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<Banner> banners = bannerService.getAllBanner(); 
        model.addAttribute("listBanners", banners); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/banners_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formBanners(Model model) { 
        model.addAttribute("banner", new Banner()); 
        return "admin/entry/banner_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteBanner(Integer id, String keyword) { 
        bannerService.removeBanner(id); 
        return "redirect:/banners/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateBanner(Model model, Integer id) { 
        Banner banner = bannerService.loadBannerById(id); 
        model.addAttribute("Banner", banner); 
        return "admin/edit/Banner_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Banner banner) { 
        bannerService.createOrUpdateBanner(banner); 
        return "redirect:/banners/index"; 
    }
 
} 
