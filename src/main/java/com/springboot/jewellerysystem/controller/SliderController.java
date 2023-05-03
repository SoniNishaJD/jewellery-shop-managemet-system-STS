package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Slider; 
import com.springboot.jewellerysystem.service.SliderService;
import com.springboot.jewellerysystem.util.FileUploadUtil;
import com.springboot.jewellerysystem.util.Helper;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession; 
@Controller 
@RequestMapping(value = "admin/slider") 
public class SliderController { 
 private SliderService sliderService; 
    public SliderController(SliderService sliderService) { 
        this.sliderService = sliderService; 
    }
 
    @GetMapping(value = "/index") 
    public String sliders(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	List<Slider> sliders = sliderService.getAllSlider(); 
        model.addAttribute("listSliders", sliders); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/sliders_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formSliders(Model model) { 
	  if(Helper.checkUserRole()) { return "redirect:/";}
  	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}  
	  model.addAttribute("slider", new Slider()); 
        return "admin/entry/slider_entry"; 
    } 
    @GetMapping(value = "/delete/{id}") 
    public String deleteSlider(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	sliderService.removeSlider(id); 
        session.setAttribute("msg", "deleted");
        return "redirect:/admin/slider/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update/{id}") 
    public String updateSlider(@PathVariable(value = "id") Integer id, Model model) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	Slider slider = sliderService.loadSliderById(id); 
        model.addAttribute("slider", slider); 
        return "admin/edit/slider_edit"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Slider slider, @RequestParam("file")MultipartFile file, HttpSession session) throws IOException { 
    	String msg = "inserted";
		if(slider.getId() != null && slider.getId() != 0) {
			msg = "updated";
		}
    	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    	
    	if(fileName.length() > 3) {
    		
		slider.setImage(fileName);
		String uploadDir = "assets1/images/slider";
		FileUploadUtil.saveFile(uploadDir, fileName, file);
    	}
    	
      Slider s =  sliderService.createOrUpdateSlider(slider); 
        if(s != null) {
			session.setAttribute("msg", "inserted");
		}else {
			session.setAttribute("error","error");
		}
        return "redirect:/admin/slider/index"; 
    }
 
} 
