package com.springboot.jewellerysystem.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.jewellerysystem.entity.Cities;
import com.springboot.jewellerysystem.entity.State;
import com.springboot.jewellerysystem.service.CitiesService;
import com.springboot.jewellerysystem.service.StateService;

@Controller
@RequestMapping(value = "cities")
public class CitiesController {
	private CitiesService citiesService;
	private StateService stateService;

	public CitiesController(CitiesService citiesService, StateService stateService) {
		this.citiesService = citiesService;
		this.stateService = stateService;
	}

	@GetMapping(value = "/index")
	public String citieses(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
		List<Cities> citieses = citiesService.getAllCities();
		model.addAttribute("listCitieses", citieses);
		model.addAttribute("keyword", keyword);
		return "admin/list/citieses_list";
	}

	@GetMapping(value = "/create")
	public String formCitieses(Model model) {
		model.addAttribute("cities", new Cities());
		List<State> states = stateService.getAllState();
		model.addAttribute("listStates", states);

		return "admin/entry/cities_entry";
	}

	@GetMapping(value = "/delete")
	public String deleteCities(Integer id, String keyword) {
		citiesService.removeCities(id);
		return "redirect:/cities/index?keyword=" + keyword;
	}

	@GetMapping(value = "/update")
	public String updateCities(Model model, Integer id) {
		Cities cities = citiesService.loadCitiesById(id);
		model.addAttribute("Cities", cities);
		List<State> states = stateService.getAllState();
		model.addAttribute("listStates", states);

		return "admin/edit/Cities_update";
	}

	@PostMapping(value = "/save")
	public String save(Cities cities) {
		citiesService.createOrUpdateCities(cities);
		return "redirect:/cities/index";
	}

}
