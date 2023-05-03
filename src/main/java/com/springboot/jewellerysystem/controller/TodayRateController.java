package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Product;
import com.springboot.jewellerysystem.entity.TodayRate;
import com.springboot.jewellerysystem.service.ProductService;
import com.springboot.jewellerysystem.service.TodayRateService;
import com.springboot.jewellerysystem.util.Helper;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List;

import javax.servlet.http.HttpSession; 
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
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	List<TodayRate> todayRates = todayRateService.getAllTodayRate(); 
        model.addAttribute("listTodayRates", todayRates); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/todayRates_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formTodayRates(Model model) { 
	  if(Helper.checkUserRole()) { return "redirect:/";}
  	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";} 
	  model.addAttribute("todayRate", new TodayRate()); 
        return "admin/entry/todayRate_entry"; 
    } 
    @GetMapping(value = "/delete/{id}") 
    public String deleteTodayRate(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	todayRateService.removeTodayRate(id); 
        session.setAttribute("msg", "deleted");
        return "redirect:/admin/todayRate/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update/{id}") 
    public String updateTodayRate(@PathVariable(value = "id") Integer id, Model model) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	TodayRate todayRate = todayRateService.loadTodayRateById(id); 
        model.addAttribute("todayRate", todayRate); 
        return "admin/edit/todayRate_edit"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(TodayRate todayRate , HttpSession session) { 
    	
    	
    	String msg = "inserted";
		if(todayRate.getId() != null && todayRate.getId() != 0) {
			msg = "updated";
		}
		TodayRate t = todayRateService.createOrUpdateTodayRate(todayRate); 
		if(t != null) {
			session.setAttribute("msg", "inserted");
		}else {
			session.setAttribute("error","error");
		}
        
        //\\//\\//\\
        List<Product> xlist = productService.getAllProduct();
		for(Product p : xlist) {
			TodayRate  rt= todayRateService.loadTodayRateById(1);
			
			float lab = p.getLabour();
			float wt = p.getGroseWeight();
			String is_fixed = p.getIsFixedLabour();
			
			float price = p.getGroseWeight()*(rt.getPrice()/10);	
			if(is_fixed == "N")
			{
				lab = wt * p.getLabour();
			}
			p.setSalesPrice(price + lab);
			 
			productService.createOrUpdateProduct(p);
		}
		//\\//\\//\\
		
        return "redirect:/admin/todayRate/index"; 
    }
 
} 
