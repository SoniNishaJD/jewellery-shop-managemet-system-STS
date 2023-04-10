package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.CompanyDetail; 
import com.springboot.jewellerysystem.service.CompanyDetailService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "companyDetail") 
public class CompanyDetailController { 
 private CompanyDetailService companyDetailService; 
    public CompanyDetailController(CompanyDetailService companyDetailService) { 
        this.companyDetailService = companyDetailService; 
    }
 
    @GetMapping(value = "/index") 
    public String companyDetails(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<CompanyDetail> companyDetails = companyDetailService.getAllCompanyDetail(); 
        model.addAttribute("listCompanyDetails", companyDetails); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/companyDetails_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formCompanyDetails(Model model) { 
        model.addAttribute("companyDetail", new CompanyDetail()); 
        return "admin/entry/companyDetail_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteCompanyDetail(Integer id, String keyword) { 
        companyDetailService.removeCompanyDetail(id); 
        return "redirect:/companyDetails/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateCompanyDetail(Model model, Integer id) { 
        CompanyDetail companyDetail = companyDetailService.loadCompanyDetailById(id); 
        model.addAttribute("CompanyDetail", companyDetail); 
        return "admin/edit/CompanyDetail_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(CompanyDetail companyDetail) { 
        companyDetailService.createOrUpdateCompanyDetail(companyDetail); 
        return "redirect:/companyDetails/index"; 
    }
 
} 
