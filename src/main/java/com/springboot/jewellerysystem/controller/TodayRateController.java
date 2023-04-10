package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.TodayRate; 
import com.springboot.jewellerysystem.service.TodayRateService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "todayRate") 
public class TodayRateController { 
 private TodayRateService todayRateService; 
    public TodayRateController(TodayRateService todayRateService) { 
        this.todayRateService = todayRateService; 
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
    @GetMapping(value = "/delete") 
    public String deleteTodayRate(Integer id, String keyword) { 
        todayRateService.removeTodayRate(id); 
        return "redirect:/todayRates/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateTodayRate(Model model, Integer id) { 
        TodayRate todayRate = todayRateService.loadTodayRateById(id); 
        model.addAttribute("TodayRate", todayRate); 
        return "admin/edit/TodayRate_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(TodayRate todayRate) { 
        todayRateService.createOrUpdateTodayRate(todayRate); 
        return "redirect:/todayRates/index"; 
    }
 
} 
