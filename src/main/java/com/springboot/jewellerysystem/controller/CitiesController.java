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

import com.springboot.jewellerysystem.entity.Cities;
import com.springboot.jewellerysystem.entity.State;
import com.springboot.jewellerysystem.service.CitiesService;
import com.springboot.jewellerysystem.service.StateService;
import com.springboot.jewellerysystem.util.Helper;

@Controller
@RequestMapping(value = "admin/cities")
public class CitiesController {
	private CitiesService citiesService;
	private StateService stateService;

	public CitiesController(CitiesService citiesService, StateService stateService) {
		this.citiesService = citiesService;
		this.stateService = stateService;
	}

	@GetMapping(value = "/index")
	public String citieses(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		List<Cities> citieses = citiesService.getAllCities();
		model.addAttribute("listCitieses", citieses);
		model.addAttribute("keyword", keyword);
		return "admin/list/citieses_list";
	}

	@GetMapping(value = "/create")
	public String formCitieses(Model model) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		model.addAttribute("cities", new Cities());
		List<State> states = stateService.getAllState();
		model.addAttribute("listStates", states);

		return "admin/entry/cities_entry";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteCities(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		citiesService.removeCities(id);
		session.setAttribute("msg", "deleted");
		return "redirect:/admin/cities/index?keyword=" + keyword;
	}

	@GetMapping(value = "/update/{id}")
	public String updateCities(@PathVariable(value = "id") Integer id, Model model) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		Cities cities = citiesService.loadCitiesById(id);
		model.addAttribute("cities", cities);
		List<State> states = stateService.getAllState();
		model.addAttribute("listStates", states);

		return "admin/edit/cities_edit";
	}

	@PostMapping(value = "/save")
	public String save(Cities cities , HttpSession session) {
		String msg = "inserted";
		if(cities.getId() != 0) {
			msg = "updated";
		}
		Cities c = citiesService.createOrUpdateCities(cities);
		if(c != null) {
			session.setAttribute("msg", "inserted");
		}else {
			session.setAttribute("error","error");
		}
		return "redirect:/admin/cities/index";
	}

}
