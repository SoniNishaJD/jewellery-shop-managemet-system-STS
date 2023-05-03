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

import com.springboot.jewellerysystem.entity.Product;
import com.springboot.jewellerysystem.entity.ProductImage;
import com.springboot.jewellerysystem.service.ProductImageService;
import com.springboot.jewellerysystem.service.ProductService;
import com.springboot.jewellerysystem.util.FileUploadUtil;
import com.springboot.jewellerysystem.util.Helper;

@Controller
@RequestMapping(value = "admin/productImage")
public class ProductImageController {
	private ProductImageService productImageService;
	private ProductService productService;

	public ProductImageController(ProductImageService productImageService, ProductService productService) {
		this.productImageService = productImageService;
		this.productService = productService;
	}

	@GetMapping(value = "/index")
	public String productImages(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		List<ProductImage> productImages = productImageService.getAllProductImage();
		model.addAttribute("listProductImages", productImages);
		model.addAttribute("keyword", keyword);
		return "admin/list/productImages_list";
	}

	@GetMapping(value = "/create")
	public String formProductImages(Model model) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		model.addAttribute("productImage", new ProductImage());
		List<Product> products = productService.getAllProduct();
		model.addAttribute("listProducts", products);

		return "admin/entry/productImage_entry";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteProductImage(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		productImageService.removeProductImage(id);
		session.setAttribute("msg", "deleted");
		return "redirect:/admin/productImage/index?keyword=" + keyword;
	}

	@GetMapping(value = "/update/{id}")
	public String updateProductImage(@PathVariable(value = "id") Integer id, Model model) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		ProductImage productImage = productImageService.loadProductImageById(id);
		model.addAttribute("productImage", productImage);
		List<Product> products = productService.getAllProduct();
		model.addAttribute("listProducts", products);

		return "admin/edit/productImage_edit";
	}

	@PostMapping(value = "/save")
	public String save(ProductImage productImage, @RequestParam("file")MultipartFile file, HttpSession session) throws IOException {
		String msg = "inserted";
		if(productImage.getId() != null && productImage.getId() != 0) {
			msg = "updated";
		}
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		if(fileName.length() > 3) {
		productImage.setImage(fileName);
		String uploadDir = "assets1/images/productImage";
		FileUploadUtil.saveFile(uploadDir, fileName, file);
		}
		
		ProductImage p =	productImageService.createOrUpdateProductImage(productImage);
		if(p != null) {
			session.setAttribute("msg", "inserted");
		}else {
			session.setAttribute("error","error");
		}

		return "redirect:/admin/productImage/index";
	}

}
