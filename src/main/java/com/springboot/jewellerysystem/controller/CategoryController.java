
package com.springboot.jewellerysystem.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.jewellerysystem.entity.Category;
import com.springboot.jewellerysystem.entity.MainCategory;
import com.springboot.jewellerysystem.service.CategoryService;
import com.springboot.jewellerysystem.service.MainCategoryService;

@Controller
@RequestMapping(value = "category")
public class CategoryController {
	private CategoryService categoryService;
	private MainCategoryService mainCategoryService;

	public CategoryController(CategoryService categoryService, MainCategoryService mainCategoryService) {
		this.categoryService = categoryService;
		this.mainCategoryService = mainCategoryService;
	}

	@GetMapping(value = "/index")
	public String categories(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
		List<Category> categories = categoryService.getAllCategory();
		model.addAttribute("listCategories", categories);
		model.addAttribute("keyword", keyword);
		return "admin/list/categories_list";
	}

	@GetMapping(value = "/create")
	public String formCategories(Model model) {
		model.addAttribute("category", new Category());
		List<MainCategory> mainCategories = mainCategoryService.getAllMainCategory();
		model.addAttribute("listMainCategories", mainCategories);

		return "admin/entry/category_entry";
	}

	@GetMapping(value = "/delete")
	public String deleteCategory(Integer id, String keyword) {
		categoryService.removeCategory(id);
		return "redirect:/category/index?keyword=" + keyword;
	}

	@GetMapping(value = "/update")
	public String updateCategory(Model model, Integer id) {
		Category category = categoryService.loadCategoryById(id);
		model.addAttribute("Category", category);
		List<MainCategory> mainCategories = mainCategoryService.getAllMainCategory();
		model.addAttribute("listMainCategories", mainCategories);

		return "admin/edit/Category_update";
	}

	@PostMapping(value = "/save")
	public String save(Category category) {
		categoryService.createOrUpdateCategory(category);
		return "redirect:/category/index";
	}

}
