package com.springboot.jewellerysystem.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.jewellerysystem.entity.Brand;
import com.springboot.jewellerysystem.service.BrandService;
import com.springboot.jewellerysystem.util.FileUploadUtil;
import com.springboot.jewellerysystem.util.Helper;

@Controller
@RequestMapping(value = "admin/brand")
public class BrandController {
	private BrandService brandService;

	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}

	@GetMapping(value = "/index")
	public String brands(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		List<Brand> brands = brandService.getAllBrand();
		model.addAttribute("listBrands", brands);
		model.addAttribute("keyword", keyword);
		return "admin/list/brands_list";
	}

	@GetMapping(value = "/create")
	public String formBrands(Model model) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		model.addAttribute("brand", new Brand());
		
		return "admin/entry/brand_entry";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteBrand(@PathVariable(value = "id") Integer id, String keyword,HttpSession session) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		brandService.removeBrand(id);
		session.setAttribute("msg", "deleted");
		return "redirect:/admin/brand/index?keyword=" + keyword;
	}

	@GetMapping(value = "/update/{id}")
	public String updateBrand(@PathVariable(value = "id") Integer id, Model model) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		Brand brand = brandService.loadBrandById(id);
		model.addAttribute("brand", brand);
		List<Brand> brands = brandService.getAllBrand();
		return "admin/edit/brand_edit";
	}

	@PostMapping(value = "/save")
	public String save(Brand brand, @RequestParam("file") MultipartFile file,HttpSession session) throws IOException {
		String msg = "inserted";
		if(brand.getId() != null && brand.getId() != 0) {
			msg = "updated";
		}
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if (fileName.length() > 3) {
			brand.setLogo(fileName);
			String uploadDir = "assets1/images/brand";
			FileUploadUtil.saveFile(uploadDir, fileName, file);

		}
		Brand b = brandService.createOrUpdateBrand(brand);
		if(b != null) {
			session.setAttribute("msg", "inserted");
		}else {
			session.setAttribute("error","error");
		}
		return "redirect:/admin/brand/index";
	}

}
