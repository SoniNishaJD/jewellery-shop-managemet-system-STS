package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Cart; 
import com.springboot.jewellerysystem.service.CartService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "cart") 
public class CartController { 
 private CartService cartService; 
    public CartController(CartService cartService) { 
        this.cartService = cartService; 
    }
 
    @GetMapping(value = "/index") 
    public String carts(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<Cart> carts = cartService.getAllCart(); 
        model.addAttribute("listCarts", carts); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/carts_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formCarts(Model model) { 
        model.addAttribute("cart", new Cart()); 
        return "admin/entry/cart_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteCart(Integer id, String keyword) { 
        cartService.removeCart(id); 
        return "redirect:/carts/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateCart(Model model, Integer id) { 
        Cart cart = cartService.loadCartById(id); 
        model.addAttribute("Cart", cart); 
        return "admin/edit/Cart_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Cart cart) { 
        cartService.createOrUpdateCart(cart); 
        return "redirect:/carts/index"; 
    }
 
} 
