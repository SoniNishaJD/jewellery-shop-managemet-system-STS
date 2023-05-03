package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.OurService; 
import com.springboot.jewellerysystem.service.OurServiceService;
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
@RequestMapping(value = "admin/ourService") 
public class OurServiceController { 
 private OurServiceService ourServiceService; 
    public OurServiceController(OurServiceService ourServiceService) { 
        this.ourServiceService = ourServiceService; 
    }
 
    @GetMapping(value = "/index") 
    public String ourServices(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	List<OurService> ourServices = ourServiceService.getAllOurService(); 
        model.addAttribute("listOurServices", ourServices); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/ourServices_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formOurServices(Model model) { 
	  if(Helper.checkUserRole()) { return "redirect:/";}
  	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";} 
	  model.addAttribute("ourService", new OurService()); 
        return "admin/entry/ourService_entry"; 
    } 
    @GetMapping(value = "/delete/{id}") 
    public String deleteOurService(@PathVariable(value = "id") Integer id, String keyword , HttpSession session) { 
        
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	ourServiceService.removeOurService(id); 
        session.setAttribute("msg", "deleted");
        return "redirect:/admin/ourService/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update/{id}") 
    public String updateOurService(@PathVariable(value = "id") Integer id, Model model) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	OurService ourService = ourServiceService.loadOurServiceById(id); 
        model.addAttribute("ourService", ourService); 
        return "admin/edit/ourService_edit"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(OurService ourService, @RequestParam("file")MultipartFile file, HttpSession session) throws IOException {
    	String msg = "inserted";
		if(ourService.getId() != null && ourService.getId() != 0) {
			msg = "updated";
		}
    	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    	
    	if(fileName.length() > 3) {
		ourService.setImage(fileName);
		String uploadDir = "assets1/images/ourService";
		FileUploadUtil.saveFile(uploadDir, fileName, file);
    	}
    	
    	OurService o= ourServiceService.createOrUpdateOurService(ourService); 
        if(o != null) {
    		session.setAttribute("msg", msg);
    	}else {
    		session.setAttribute("error","error");
    	}
        return "redirect:/admin/ourService/index"; 
    }
 
} 
