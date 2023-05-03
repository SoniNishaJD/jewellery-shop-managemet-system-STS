package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Currency; 
import com.springboot.jewellerysystem.service.CurrencyService;
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
@RequestMapping(value = "admin/currency") 
public class CurrencyController { 
 private CurrencyService currencyService; 
    public CurrencyController(CurrencyService currencyService) { 
        this.currencyService = currencyService; 
    }
 
    @GetMapping(value = "/index") 
    public String currencies(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	List<Currency> currencies = currencyService.getAllCurrency(); 
        model.addAttribute("listCurrencies", currencies); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/currencies_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formCurrencies(Model model) { 
	  if(Helper.checkUserRole()) { return "redirect:/";}
  	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";} 
	  model.addAttribute("currency", new Currency()); 
        return "admin/entry/currency_entry"; 
    } 
    @GetMapping(value = "/delete/{id}") 
    public String deleteCurrency(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	currencyService.removeCurrency(id); 
        session.setAttribute("msg", "deleted");
        return "redirect:/admin/currency/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update/{id}") 
    public String updateCurrency(@PathVariable(value = "id") Integer id, Model model) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	Currency currency = currencyService.loadCurrencyById(id); 
        model.addAttribute("currency", currency); 
        return "admin/edit/currency_edit"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Currency currency, HttpSession session) { 
    	String msg = "inserted";
		if(currency.getId() != null && currency.getId() != 0) {
			msg = "updated";
		}
		Currency c =  currencyService.createOrUpdateCurrency(currency); 
		if(c != null) {
			session.setAttribute("msg", msg);
		}else {
			session.setAttribute("error","error");
		}
        return "redirect:/admin/currency/index"; 
    }
 
} 
