package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Link; 
import com.springboot.jewellerysystem.service.LinkService;
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
@RequestMapping(value = "admin/link") 
public class LinkController { 
 private LinkService linkService; 
    public LinkController(LinkService linkService) { 
        this.linkService = linkService; 
    }
 
    @GetMapping(value = "/index") 
    public String links(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	List<Link> links = linkService.getAllLink(); 
        model.addAttribute("listLinks", links); 
        model.addAttribute("keyword", keyword); 
        return "admin/list/links_list"; 
    }
 
  @GetMapping(value = "/create") 
    public String formLinks(Model model) { 
	  if(Helper.checkUserRole()) { return "redirect:/";}
  	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";} 
	  model.addAttribute("link", new Link()); 
        return "admin/entry/link_entry"; 
    } 
    @GetMapping(value = "/delete/{id}") 
    public String deleteLink(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	linkService.removeLink(id); 
        session.setAttribute("msg", "deleted");
        return "redirect:/admin/link/index?keyword=" + keyword; 
    }
 
    @GetMapping(value = "/update/{id}") 
    public String updateLink(@PathVariable(value = "id") Integer id, Model model) { 
    	if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
    	Link link = linkService.loadLinkById(id); 
        model.addAttribute("link", link); 
        return "admin/edit/link_edit"; 
    }
 
    @PostMapping(value = "/save") 
    public String save(Link link, HttpSession session) { 
    	String msg = "inserted";
		if(link.getId() != null && link.getId() != 0) {
			msg = "updated";
		}
    	
		Link l =  linkService.createOrUpdateLink(link); 
        if(l != null) {
    		session.setAttribute("msg", msg);
    	}else {
    		session.setAttribute("error","error");
    	}
        return "redirect:/admin/link/index"; 
    }
 
} 
