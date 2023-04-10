package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.ProductImage; 
import com.springboot.jewellerysystem.service.ProductImageService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "productImage") 
public class ProductImageController { 
 private ProductImageService productImageService; 
    public ProductImageController(ProductImageService productImageService) { 
        this.productImageService = productImageService; 
    }
 
    @GetMapping(value = "/index") 
    public String productImages(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<ProductImage> productImages = productImageService.getAllProductImage(); 
        model.addAttribute("listProductImages", productImages); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/productImages_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formProductImages(Model model) { 
        model.addAttribute("productImage", new ProductImage()); 
        return "admin/entry/productImage_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteProductImage(Integer id, String keyword) { 
        productImageService.removeProductImage(id); 
        return "redirect:/productImages/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateProductImage(Model model, Integer id) { 
        ProductImage productImage = productImageService.loadProductImageById(id); 
        model.addAttribute("ProductImage", productImage); 
        return "admin/edit/ProductImage_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(ProductImage productImage) { 
        productImageService.createOrUpdateProductImage(productImage); 
        return "redirect:/productImages/index"; 
    }
 
} 
