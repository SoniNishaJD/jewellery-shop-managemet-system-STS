package com.springboot.jewellerysystem.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.jewellerysystem.entity.Product;
import com.springboot.jewellerysystem.entity.ProductImage;
import com.springboot.jewellerysystem.service.ProductImageService;
import com.springboot.jewellerysystem.service.ProductService;

@Controller
@RequestMapping(value = "productImage")
public class ProductImageController {
	private ProductImageService productImageService;
	private ProductService productService;

	public ProductImageController(ProductImageService productImageService, ProductService productService) {
		this.productImageService = productImageService;
		this.productService = productService;
	}

	@GetMapping(value = "/index")
	public String productImages(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
		List<ProductImage> productImages = productImageService.getAllProductImage();
		model.addAttribute("listProductImages", productImages);
		model.addAttribute("keyword", keyword);
		return "admin/list/productImages_list";
	}

	@GetMapping(value = "/create")
	public String formProductImages(Model model) {
		model.addAttribute("productImage", new ProductImage());
		List<Product> products = productService.getAllProduct();
		model.addAttribute("listProducts", products);

		return "admin/entry/productImage_entry";
	}

	@GetMapping(value = "/delete")
	public String deleteProductImage(Integer id, String keyword) {
		productImageService.removeProductImage(id);
		return "redirect:/productImages/index?keyword=" + keyword;
	}

	@GetMapping(value = "/update")
	public String updateProductImage(Model model, Integer id) {
		ProductImage productImage = productImageService.loadProductImageById(id);
		model.addAttribute("ProductImage", productImage);
		List<Product> products = productService.getAllProduct();
		model.addAttribute("listProducts", products);

		return "admin/edit/ProductImage_update";
	}

	@PostMapping(value = "/save")
	public String save(ProductImage productImage) {
		productImageService.createOrUpdateProductImage(productImage);
		return "redirect:/productImages/index";
	}

}
