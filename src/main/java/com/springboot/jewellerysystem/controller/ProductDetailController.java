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
import com.springboot.jewellerysystem.entity.ProductDetail;
import com.springboot.jewellerysystem.service.ProductDetailService;
import com.springboot.jewellerysystem.service.ProductService;
import com.springboot.jewellerysystem.util.Helper;

@Controller
@RequestMapping(value = "admin/productDetail")
public class ProductDetailController {
	private ProductDetailService productDetailService;
	private ProductService productService;

	public ProductDetailController(ProductDetailService productDetailService, ProductService productService) {
		this.productDetailService = productDetailService;
		this.productService = productService;
	}

	@GetMapping(value = "/index")
	public String productDetails(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		List<ProductDetail> productDetails = productDetailService.getAllProductDetail();
		model.addAttribute("listProductDetails", productDetails);
		model.addAttribute("keyword", keyword);
		return "admin/list/productDetails_list";
	}

	@GetMapping(value = "/create")
	public String formProductDetails(Model model) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		model.addAttribute("productDetail", new ProductDetail());
		List<Product> products = productService.getAllProduct();
		model.addAttribute("listProducts", products);

		return "admin/entry/productDetail_entry";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteProductDetail(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		productDetailService.removeProductDetail(id);
		session.setAttribute("msg", "deleted");
		return "redirect:/admin/productDetail/index?keyword=" + keyword;
	}

	@GetMapping(value = "/update/{id}")
	public String updateProductDetail(@PathVariable(value = "id") Integer id, Model model) {
		if(Helper.checkUserRole()) { return "redirect:/";}
    	if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		ProductDetail productDetail = productDetailService.loadProductDetailById(id);
		model.addAttribute("productDetail", productDetail);
		List<Product> products = productService.getAllProduct();
		model.addAttribute("listProducts", products);

		return "admin/edit/productDetail_edit";
	}

	@PostMapping(value = "/save")
	public String save(ProductDetail productDetail, HttpSession session) {
		String msg = "inserted";
		if(productDetail.getId() != null && productDetail.getId() != 0) {
			msg = "updated";
		}
		ProductDetail p =	productDetailService.createOrUpdateProductDetail(productDetail);
		if(p != null) {
			session.setAttribute("msg", "inserted");
		}else {
			session.setAttribute("error","error");
		}
		return "redirect:/admin/productDetail/index";
	}

}
