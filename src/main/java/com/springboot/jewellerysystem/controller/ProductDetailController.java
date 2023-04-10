package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.ProductDetail; 
import com.springboot.jewellerysystem.service.ProductDetailService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "productDetail") 
public class ProductDetailController { 
 private ProductDetailService productDetailService; 
    public ProductDetailController(ProductDetailService productDetailService) { 
        this.productDetailService = productDetailService; 
    }
 
    @GetMapping(value = "/index") 
    public String productDetails(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<ProductDetail> productDetails = productDetailService.getAllProductDetail(); 
        model.addAttribute("listProductDetails", productDetails); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/productDetails_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formProductDetails(Model model) { 
        model.addAttribute("productDetail", new ProductDetail()); 
        return "admin/entry/productDetail_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteProductDetail(Integer id, String keyword) { 
        productDetailService.removeProductDetail(id); 
        return "redirect:/productDetails/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateProductDetail(Model model, Integer id) { 
        ProductDetail productDetail = productDetailService.loadProductDetailById(id); 
        model.addAttribute("ProductDetail", productDetail); 
        return "admin/edit/ProductDetail_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(ProductDetail productDetail) { 
        productDetailService.createOrUpdateProductDetail(productDetail); 
        return "redirect:/productDetails/index"; 
    }
 
} 
