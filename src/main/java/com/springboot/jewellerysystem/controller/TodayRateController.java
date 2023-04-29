package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Product;
import com.springboot.jewellerysystem.entity.TodayRate;
import com.springboot.jewellerysystem.service.ProductService;
import com.springboot.jewellerysystem.service.TodayRateService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "admin/todayRate") 
public class TodayRateController { 
 private TodayRateService todayRateService; 
 private ProductService productService;
    public TodayRateController(TodayRateService todayRateService, ProductService productService) { 
        this.todayRateService = todayRateService; 
        this.productService = productService;
    }
 
    @GetMapping(value = "/index") 
    public String todayRates(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<TodayRate> todayRates = todayRateService.getAllTodayRate(); 
        model.addAttribute("listTodayRates", todayRates); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/todayRates_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formTodayRates(Model model) { 
        model.addAttribute("todayRate", new TodayRate()); 
        return "admin/entry/todayRate_entry"; 
    } 
    @GetMapping(value = "/delete/{id}") 
    public String deleteTodayRate(@PathVariable(value = "id") Integer id, String keyword) { 
        todayRateService.removeTodayRate(id); 
        return "redirect:/admin/todayRate/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update/{id}") 
    public String updateTodayRate(@PathVariable(value = "id") Integer id, Model model) { 
        TodayRate todayRate = todayRateService.loadTodayRateById(id); 
        model.addAttribute("todayRate", todayRate); 
        return "admin/edit/todayRate_edit"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(TodayRate todayRate) { 
        todayRateService.createOrUpdateTodayRate(todayRate); 
        
        //\\//\\//\\
        List<Product> xlist = productService.getAllProduct();
		for(Product p : xlist) {
			TodayRate  rt= todayRateService.loadTodayRateById(1);
			p.setSalesPrice(p.getGroseWeight()*rt.getPrice()/10);
			productService.createOrUpdateProduct(p);
		}
		//\\//\\//\\
        return "redirect:/admin/todayRate/index"; 
    }
 
} 
