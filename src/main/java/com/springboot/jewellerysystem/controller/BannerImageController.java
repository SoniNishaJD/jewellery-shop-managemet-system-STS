package com.springboot.jewellerysystem.controller;

import com.springboot.jewellerysystem.entity.Banner;
import com.springboot.jewellerysystem.entity.BannerImage;
import com.springboot.jewellerysystem.service.BannerImageService;
import com.springboot.jewellerysystem.service.BannerService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping(value = "bannerImage")
public class BannerImageController {
	private BannerImageService bannerImageService;
	
	private BannerService bannerService;
	
	

	public BannerImageController(BannerImageService bannerImageService, BannerService bannerService) {
		super();
		this.bannerImageService = bannerImageService;
		this.bannerService = bannerService;
	}

	
	@GetMapping(value = "/index")
	public String bannerImages(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
		List<BannerImage> bannerImages = bannerImageService.getAllBannerImage();
		model.addAttribute("listBannerImages", bannerImages);
		model.addAttribute("keyword", keyword);
		return "admin/list/bannerImages_list";
	}

	@GetMapping(value = "/create")
	public String formBannerImages(Model model) {
		model.addAttribute("bannerImage", new BannerImage());
		List<Banner> banners = bannerService.getAllBanner(); 
        model.addAttribute("listBanners", banners); 
		return "admin/entry/bannerImage_entry";
	}

	@GetMapping(value = "/delete")
	public String deleteBannerImage(Integer id, String keyword) {
		bannerImageService.removeBannerImage(id);
		return "redirect:/bannerImages/index?keyword=" + keyword;
	}

	@GetMapping(value = "/update")
	public String updateBannerImage(Model model, Integer id) {
		BannerImage bannerImage = bannerImageService.loadBannerImageById(id);
		model.addAttribute("BannerImage", bannerImage);
		return "admin/edit/BannerImage_update";
	}

	@PostMapping(value = "/save")
	public String save(BannerImage bannerImage) {
		bannerImageService.createOrUpdateBannerImage(bannerImage);
		return "redirect:/bannerImages/index";
	}

}
