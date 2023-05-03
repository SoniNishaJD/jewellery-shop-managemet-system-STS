package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Style; 
import com.springboot.jewellerysystem.service.StyleService;
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
@RequestMapping(value = "admin/style") 
public class StyleController { 
 private StyleService styleService; 
    public StyleController(StyleService styleService) { 
        this.styleService = styleService; 
    }
 
    @GetMapping(value = "/index") 
    public String styles(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	List<Style> styles = styleService.getAllStyle(); 
        model.addAttribute("listStyles", styles); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/styles_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formStyles(Model model) { 
	  if(Helper.checkUserRole()) { return "redirect:/";}
  	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}  
	  model.addAttribute("style", new Style()); 
        return "admin/entry/style_entry"; 
    } 
    @GetMapping(value = "/delete/{id}") 
    public String deleteStyle(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	styleService.removeStyle(id); 
        session.setAttribute("msg", "deleted");
        return "redirect:/admin/style/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update/{id}") 
    public String updateStyle(@PathVariable(value = "id") Integer id, Model model) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	Style style = styleService.loadStyleById(id); 
        model.addAttribute("style", style); 
        return "admin/edit/style_edit"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Style style, @RequestParam("file")MultipartFile file, HttpSession session) throws IOException {
    	String msg = "inserted";
		if(style.getId() != null && style.getId() != 0) {
			msg = "updated";
		}
    	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    	
    	if(fileName.length() > 3) {
		style.setImage(fileName);
		String uploadDir = "assets1/images/style";
		FileUploadUtil.saveFile(uploadDir, fileName, file);
    	}
    	
    	Style s =  styleService.createOrUpdateStyle(style); 
        if(s != null) {
			session.setAttribute("msg", "inserted");
		}else {
			session.setAttribute("error","error");
		}
        return "redirect:/admin/style/index"; 
    }
 
} 
