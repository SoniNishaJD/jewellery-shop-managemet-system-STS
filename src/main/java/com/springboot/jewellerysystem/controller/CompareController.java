package com.springboot.jewellerysystem.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.jewellerysystem.entity.Product;
import com.springboot.jewellerysystem.entity.User;
import com.springboot.jewellerysystem.entity.Compare;
import com.springboot.jewellerysystem.service.ProductService;
import com.springboot.jewellerysystem.service.UserService;
import com.springboot.jewellerysystem.util.Helper;
import com.springboot.jewellerysystem.service.CompareService;

@Controller
@RequestMapping(value = "admin/Compare")
public class CompareController {
	private CompareService compareService;
	private ProductService productService;
	private UserService userService;

	public CompareController(CompareService compareService, ProductService productService, UserService userService) {
		this.compareService = compareService;
		this.productService = productService;
		this.userService = userService;
	}

	@GetMapping(value = "/index")
	public String Compares(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		List<Compare> compares = compareService.getAllCompare();
		model.addAttribute("listCompares", compares);
		model.addAttribute("keyword", keyword);
		return "admin/list/compares_list";
	}

	@GetMapping(value = "/create")
	public String formCompares(Model model) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		model.addAttribute("Compare", new Compare());
		List<Product> products = productService.getAllProduct();
		model.addAttribute("listProducts", products);

		List<User> users = userService.getAllUser();
		model.addAttribute("listUsers", users);

		return "admin/entry/compare_entry";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteCompare(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		compareService.removeCompare(id);
		session.setAttribute("msg", "deleted");
		return "redirect:/admin/compare/index?keyword=" + keyword;
	}

	@GetMapping(value = "/update/{id}")
	public String updateCompare(@PathVariable(value = "id") Integer id, Model model) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		Compare compare = compareService.loadCompareById(id);
		model.addAttribute("Compare", compare);
		List<Product> products = productService.getAllProduct();
		model.addAttribute("listProducts", products);

		List<User> users = userService.getAllUser();
		model.addAttribute("listUsers", users);

		return "admin/edit/compare_edit";
	}

	@PostMapping(value = "/save")
	public String save(Compare compare, HttpSession session) {
		String msg = "inserted";
		if(compare.getId() != null && compare.getId() != 0) {
			msg = "updated";
		}
		Compare c = compareService.createOrUpdateCompare(compare);
		if(c != null) {
			session.setAttribute("msg", "inserted");
		}else {
			session.setAttribute("error","error");
		}
		return "redirect:/admin/compare/index";
	}

}
