package com.springboot.jewellerysystem.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.jewellerysystem.entity.CompanyDetail;
import com.springboot.jewellerysystem.service.CompanyDetailService;
import com.springboot.jewellerysystem.util.FileUploadUtil;
import com.springboot.jewellerysystem.util.Helper;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "admin/companyDetail")
public class CompanyDetailController {
	private CompanyDetailService companyDetailService;

	public CompanyDetailController(CompanyDetailService companyDetailService) {
		this.companyDetailService = companyDetailService;
	}

	@GetMapping(value = "/index")
	public String companyDetails(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		List<CompanyDetail> companyDetails = companyDetailService.getAllCompanyDetail();
		model.addAttribute("listCompanyDetails", companyDetails);
		model.addAttribute("keyword", keyword);
		return "admin/list/companyDetails_list";
	}

	@GetMapping(value = "/create")
	public String formCompanyDetails(Model model) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		model.addAttribute("companyDetail", new CompanyDetail());
		return "admin/entry/companyDetail_entry";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteCompanyDetail(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		companyDetailService.removeCompanyDetail(id);
		session.setAttribute("msg", "deleted");
		return "redirect:/admin/companyDetail/index?keyword=" + keyword;
	}

	@GetMapping(value = "/update/{id}")
	public String updateCompanyDetail(@PathVariable(value = "id") Integer id, Model model) {
		
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}CompanyDetail companyDetail = companyDetailService.loadCompanyDetailById(id);
		model.addAttribute("companyDetail", companyDetail);
		List<CompanyDetail> companyDetails = companyDetailService.getAllCompanyDetail();
		model.addAttribute("listCompanyDetails", companyDetails);
		return "admin/edit/companyDetail_edit";
	}

	@PostMapping(value = "/save")
	public String save(CompanyDetail companyDetail, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
		String msg = "inserted";
		if(companyDetail.getId() != 0) {
			msg = "updated";
		}
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if (fileName.length() > 3) {
		companyDetail.setLogo(fileName);
		String uploadDir = "assets1/images/companyDetail";
		FileUploadUtil.saveFile(uploadDir, fileName, file);
		}
		
		CompanyDetail c = companyDetailService.createOrUpdateCompanyDetail(companyDetail);
		if(c != null) {
			session.setAttribute("msg", "inserted");
		}else {
			session.setAttribute("error","error");
		}
		return "redirect:/admin/companyDetail/index";
	}

}
