package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Wishlist; 
import com.springboot.jewellerysystem.service.WishlistService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "wishlist") 
public class WishlistController { 
 private WishlistService wishlistService; 
    public WishlistController(WishlistService wishlistService) { 
        this.wishlistService = wishlistService; 
    }
 
    @GetMapping(value = "/index") 
    public String wishlists(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<Wishlist> wishlists = wishlistService.getAllWishlist(); 
        model.addAttribute("listWishlists", wishlists); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/wishlists_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formWishlists(Model model) { 
        model.addAttribute("wishlist", new Wishlist()); 
        return "admin/entry/wishlist_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteWishlist(Integer id, String keyword) { 
        wishlistService.removeWishlist(id); 
        return "redirect:/wishlists/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateWishlist(Model model, Integer id) { 
        Wishlist wishlist = wishlistService.loadWishlistById(id); 
        model.addAttribute("Wishlist", wishlist); 
        return "admin/edit/Wishlist_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Wishlist wishlist) { 
        wishlistService.createOrUpdateWishlist(wishlist); 
        return "redirect:/wishlists/index"; 
    }
 
} 
