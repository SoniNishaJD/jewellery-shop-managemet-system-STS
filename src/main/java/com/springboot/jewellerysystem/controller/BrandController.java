package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Brand; 
import com.springboot.jewellerysystem.service.BrandService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "brand") 
public class BrandController { 
 private BrandService brandService; 
    public BrandController(BrandService brandService) { 
        this.brandService = brandService; 
    }
 
    @GetMapping(value = "/index") 
    public String brands(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<Brand> brands = brandService.getAllBrand(); 
        model.addAttribute("listBrands", brands); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/brands_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formBrands(Model model) { 
        model.addAttribute("brand", new Brand()); 
        return "admin/entry/brand_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteBrand(Integer id, String keyword) { 
        brandService.removeBrand(id); 
        return "redirect:/brands/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateBrand(Model model, Integer id) { 
        Brand brand = brandService.loadBrandById(id); 
        model.addAttribute("Brand", brand); 
        return "admin/edit/Brand_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Brand brand) { 
        brandService.createOrUpdateBrand(brand); 
        return "redirect:/brands/index"; 
    }
 
} 
