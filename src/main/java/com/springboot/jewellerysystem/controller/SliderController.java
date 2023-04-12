package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Slider; 
import com.springboot.jewellerysystem.service.SliderService; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.util.List; 
@Controller 
@RequestMapping(value = "slider") 
public class SliderController { 
 private SliderService sliderService; 
    public SliderController(SliderService sliderService) { 
        this.sliderService = sliderService; 
    }
 
    @GetMapping(value = "/index") 
    public String sliders(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
        List<Slider> sliders = sliderService.getAllSlider(); 
        model.addAttribute("listSliders", sliders); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/sliders_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formSliders(Model model) { 
        model.addAttribute("slider", new Slider()); 
        return "admin/entry/slider_entry"; 
    } 
    @GetMapping(value = "/delete") 
    public String deleteSlider(Integer id, String keyword) { 
        sliderService.removeSlider(id); 
        return "redirect:/slider/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update") 
    public String updateSlider(Model model, Integer id) { 
        Slider slider = sliderService.loadSliderById(id); 
        model.addAttribute("Slider", slider); 
        return "admin/edit/Slider_update"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Slider slider) { 
        sliderService.createOrUpdateSlider(slider); 
        return "redirect:/slider/index"; 
    }
 
} 
