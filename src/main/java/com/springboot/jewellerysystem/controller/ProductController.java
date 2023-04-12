package com.springboot.jewellerysystem.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.jewellerysystem.entity.Brand;
import com.springboot.jewellerysystem.entity.Category;
import com.springboot.jewellerysystem.entity.Product;
import com.springboot.jewellerysystem.service.BrandService;
import com.springboot.jewellerysystem.service.CategoryService;
import com.springboot.jewellerysystem.service.ProductService;

@Controller
@RequestMapping(value = "product")
public class ProductController {
	private ProductService productService;
	private BrandService brandService;
	private CategoryService categoryService;

	public ProductController(ProductService productService, BrandService brandService,
			CategoryService categoryService) {
		this.productService = productService;
		this.brandService = brandService;
		this.categoryService = categoryService;
	}

	@GetMapping(value = "/index")
	public String products(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
		List<Product> products = productService.getAllProduct();
		model.addAttribute("listProducts", products);
		model.addAttribute("keyword", keyword);
		return "admin/list/products_list";
	}

	@GetMapping(value = "/create")
	public String formProducts(Model model) {
		model.addAttribute("product", new Product());
		List<Brand> brands = brandService.getAllBrand();
		model.addAttribute("listBrands", brands);

		List<Category> categories = categoryService.getAllCategory();
		model.addAttribute("listCategories", categories);

		return "admin/entry/product_entry";
	}

	@GetMapping(value = "/delete")
	public String deleteProduct(Integer id, String keyword) {
		productService.removeProduct(id);
		return "redirect:/product/index?keyword=" + keyword;
	}

	@GetMapping(value = "/update")
	public String updateProduct(Model model, Integer id) {
		Product product = productService.loadProductById(id);
		model.addAttribute("Product", product);
		List<Brand> brands = brandService.getAllBrand();
		model.addAttribute("listBrands", brands);

		List<Category> categories = categoryService.getAllCategory();
		model.addAttribute("listCategories", categories);

		return "admin/edit/Product_update";
	}

	@PostMapping(value = "/save")
	public String save(Product product) {
		productService.createOrUpdateProduct(product);
		return "redirect:/product/index";
	}

}
