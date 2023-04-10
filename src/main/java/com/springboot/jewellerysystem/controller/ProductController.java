package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Product; 
import com.springboot.jewellerysystem.service.ProductService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "product") 
public class ProductController { 
 private ProductService productService; 
    public ProductController(ProductService productService) { 
        this.productService = productService; 
    }
 
    @GetMapping(value = "/index") 
    public String products(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<Product> products = productService.getAllProduct(); 
        model.addAttribute("listProducts", products); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/products_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formProducts(Model model) { 
        model.addAttribute("product", new Product()); 
        return "admin/entry/product_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteProduct(Integer id, String keyword) { 
        productService.removeProduct(id); 
        return "redirect:/products/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateProduct(Model model, Integer id) { 
        Product product = productService.loadProductById(id); 
        model.addAttribute("Product", product); 
        return "admin/edit/Product_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Product product) { 
        productService.createOrUpdateProduct(product); 
        return "redirect:/products/index"; 
    }
 
} 
