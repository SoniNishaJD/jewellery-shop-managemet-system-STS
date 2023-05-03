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

import com.springboot.jewellerysystem.entity.Banner;
import com.springboot.jewellerysystem.entity.BannerType;
import com.springboot.jewellerysystem.service.BannerService;
import com.springboot.jewellerysystem.service.BannerTypeService;
import com.springboot.jewellerysystem.util.Helper;

@Controller
@RequestMapping(value = "admin/banner")
public class BannerController {
	private BannerService bannerService;
	private BannerTypeService bannerTypeService;

	public BannerController(BannerService bannerService, BannerTypeService bannerTypeService) {
		this.bannerService = bannerService;
		this.bannerTypeService = bannerTypeService;
	}

	@GetMapping(value = "/index")
	public String banners(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
		if(Helper.checkUserRole()) { return "redirect:/";}
		if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		
		List<Banner> banners = bannerService.getAllBanner();
		model.addAttribute("listBanners", banners);
		model.addAttribute("keyword", keyword);
		return "admin/list/banners_list";
	}

	@GetMapping(value = "/create")
	public String formBanners(Model model) {
		if(Helper.checkUserRole()) { return "redirect:/";}
		if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		
		
		model.addAttribute("banner", new Banner());
		List<BannerType> bannerTypes = bannerTypeService.getAllBannerType();
		model.addAttribute("listBannerTypes", bannerTypes);

		return "admin/entry/banner_entry";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteBanner(@PathVariable(value = "id") Integer id, String keyword,HttpSession session) {
		if(Helper.checkUserRole()) { return "redirect:/";}
		if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		
		bannerService.removeBanner(id);
		session.setAttribute("msg", "deleted");
		return "redirect:/admin/banner/index?keyword=" + keyword;
	}

	@GetMapping(value = "/update/{id}")
	public String updateBanner(@PathVariable(value = "id") Integer id, Model model) {
		Banner banner = bannerService.loadBannerById(id);
		model.addAttribute("banner", banner);
		List<BannerType> bannerTypes = bannerTypeService.getAllBannerType();
		model.addAttribute("listBannerTypes", bannerTypes);

		return "admin/edit/banner_edit";
	}

	@PostMapping(value = "/save")
	public String save(Banner banner, HttpSession session) {
		String msg = "inserted";
		if(banner.getId() != null && banner.getId() != 0) {
			msg = "updated";
		}
		Banner b = bannerService.createOrUpdateBanner(banner);
		if(b != null) {
			session.setAttribute("msg", "inserted");
		}else {
			session.setAttribute("error","error");
		}
		
		return "redirect:/admin/banner/index";
	}

}
