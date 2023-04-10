package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Currency; 
import com.springboot.jewellerysystem.service.CurrencyService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "currency") 
public class CurrencyController { 
 private CurrencyService currencyService; 
    public CurrencyController(CurrencyService currencyService) { 
        this.currencyService = currencyService; 
    }
 
    @GetMapping(value = "/index") 
    public String currencies(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<Currency> currencies = currencyService.getAllCurrency(); 
        model.addAttribute("listCurrencies", currencies); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/currencies_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formCurrencies(Model model) { 
        model.addAttribute("currency", new Currency()); 
        return "admin/entry/currency_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteCurrency(Integer id, String keyword) { 
        currencyService.removeCurrency(id); 
        return "redirect:/currencies/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateCurrency(Model model, Integer id) { 
        Currency currency = currencyService.loadCurrencyById(id); 
        model.addAttribute("Currency", currency); 
        return "admin/edit/Currency_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Currency currency) { 
        currencyService.createOrUpdateCurrency(currency); 
        return "redirect:/currencies/index"; 
    }
 
} 
