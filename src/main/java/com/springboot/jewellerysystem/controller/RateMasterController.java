package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.RateMaster; 
import com.springboot.jewellerysystem.service.RateMasterService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "rateMaster") 
public class RateMasterController { 
 private RateMasterService rateMasterService; 
    public RateMasterController(RateMasterService rateMasterService) { 
        this.rateMasterService = rateMasterService; 
    }
 
    @GetMapping(value = "/index") 
    public String rateMasters(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<RateMaster> rateMasters = rateMasterService.getAllRateMaster(); 
        model.addAttribute("listRateMasters", rateMasters); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/rateMasters_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formRateMasters(Model model) { 
        model.addAttribute("rateMaster", new RateMaster()); 
        return "admin/entry/rateMaster_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteRateMaster(Integer id, String keyword) { 
        rateMasterService.removeRateMaster(id); 
        return "redirect:/rateMaster/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateRateMaster(Model model, Integer id) { 
        RateMaster rateMaster = rateMasterService.loadRateMasterById(id); 
        model.addAttribute("RateMaster", rateMaster); 
        return "admin/edit/RateMaster_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(RateMaster rateMaster) { 
        rateMasterService.createOrUpdateRateMaster(rateMaster); 
        return "redirect:/rateMaster/index"; 
    }
 
} 
