package com.springboot.jewellerysystem.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.springboot.jewellerysystem.entity.Category;
import com.springboot.jewellerysystem.entity.Product;
import com.springboot.jewellerysystem.service.BrandService;
import com.springboot.jewellerysystem.service.CategoryService;
import com.springboot.jewellerysystem.service.ProductService;
import com.springboot.jewellerysystem.util.FileUploadUtil;
import com.springboot.jewellerysystem.util.Helper;

@Controller
@RequestMapping(value = "admin/product")
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
		if (Helper.checkUserRole()) {
			return "redirect:/";
		}
		if (!Helper.checkAdminRole()) {
			return "redirect:/admin/logout";
		}
		List<Product> products = productService.getAllProduct();
		model.addAttribute("listProducts", products);
		model.addAttribute("keyword", keyword);
		return "admin/list/products_list";
	}

	@GetMapping(value = "/create")
	public String formProducts(Model model) {
		if (Helper.checkUserRole()) {
			return "redirect:/";
		}
		if (!Helper.checkAdminRole()) {
			return "redirect:/admin/logout";
		}
		model.addAttribute("product", new Product());
		List<Brand> brands = brandService.getAllBrand();
		model.addAttribute("listBrands", brands);

		List<Category> categories = categoryService.getAllCategory();
		model.addAttribute("listCategories", categories);

		return "admin/entry/product_entry";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteProduct(@PathVariable(value = "id") Integer id, String keyword, HttpSession session) {
		if (Helper.checkUserRole()) {
			return "redirect:/";
		}
		if (!Helper.checkAdminRole()) {
			return "redirect:/admin/logout";
		}
		productService.removeProduct(id);
		session.setAttribute("msg", "deleted");
		session.setAttribute("msg", "deleted");
		return "redirect:/admin/product/index?keyword=" + keyword;
	}

	@GetMapping(value = "/update/{id}")
	public String updateProduct(@PathVariable(value = "id") Integer id, Model model) {
		if (Helper.checkUserRole()) {
			return "redirect:/";
		}
		if (!Helper.checkAdminRole()) {
			return "redirect:/admin/logout";
		}
		Product product = productService.loadProductById(id);
		model.addAttribute("product", product);
		List<Brand> brands = brandService.getAllBrand();
		model.addAttribute("listBrands", brands);

		List<Category> categories = categoryService.getAllCategory();
		model.addAttribute("listCategories", categories);

		return "admin/edit/product_edit";
	}

	@PostMapping(value = "/save")
	public String save(Product product, @RequestParam("file") MultipartFile file, HttpSession session)
			throws IOException {
		String msg = "inserted";
		if (product.getId() != null && product.getId() != 0) {
			msg = "updated";
		}
		String fileTime = new SimpleDateFormat("yyyyMMddHHmmssms").format(new Date());

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		if (fileName.length() > 3) {
			fileName = fileTime + fileName;
			product.setImage(fileName);
			String uploadDir = "assets1/images/product";
			FileUploadUtil.saveFile(uploadDir, fileName, file);
		}else {
			if (product.getId() == null) {
				product.setImage("no-img.png");
			}
		}

		Product p = productService.createOrUpdateProduct(product);
		if (p != null) {
			session.setAttribute("msg", msg);
		} else {
			session.setAttribute("error", "error");
		}

		return "redirect:/admin/product/index";
	}

}
