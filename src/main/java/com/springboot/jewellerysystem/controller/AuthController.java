package com.springboot.jewellerysystem.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.springboot.jewellerysystem.entity.User;
import com.springboot.jewellerysystem.service.BrandService;
import com.springboot.jewellerysystem.service.CategoryService;
import com.springboot.jewellerysystem.service.MainCategoryService;
import com.springboot.jewellerysystem.service.OrderService;
import com.springboot.jewellerysystem.service.ProductService;
import com.springboot.jewellerysystem.service.UserService;
import com.springboot.jewellerysystem.util.Helper;

@Controller
@RequestMapping("/admin")
public class AuthController {
	UserService userService;
	ProductService productService;
	OrderService orderService;
	MainCategoryService mainCategoryService;
	CategoryService categoryService;
	BrandService brandService;

	public AuthController(UserService userService, ProductService productService, OrderService orderService,
			MainCategoryService mainCategoryService, CategoryService categoryService, BrandService brandService) {
		super();
		this.productService = productService;
		this.orderService = orderService;
		this.mainCategoryService = mainCategoryService;
		this.categoryService = categoryService;
		this.brandService = brandService;
		this.userService = userService;
	}

	@GetMapping()
	public String getDashboard(Model m) {

		if(Helper.checkUserRole()) { return "redirect:/";}
		if(!Helper.checkAdminRole()) {return "redirect:/admin/logout";}
		
		int p_count = productService.getAllProduct().size();
		int o_count = orderService.getAllOrder().size();
		int m_count = mainCategoryService.getAllMainCategory().size();
		int c_count = categoryService.getAllCategory().size();
		int b_count = brandService.getAllBrand().size();

		m.addAttribute("product_count", p_count);
		m.addAttribute("order_count", o_count);
		m.addAttribute("mainCategory_count", m_count);
		m.addAttribute("category_count", c_count);
		m.addAttribute("brand_count", b_count);

		return "admin/dashboard";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/admin/login";
	}

	@PostMapping("/login")
	public String CheckLogin(@RequestParam("email") String email, @RequestParam("password") String passord,
			HttpSession session) {
		
		User user = userService.getUserByEmailandPassword(email, passord);
		if (user != null) {
			session.setAttribute("uname", user.getFirstName() + " " + user.getLastName());
			session.setAttribute("uid", user.getId());
			session.setAttribute("urole", user.getRole());
			System.out.println("xxxxxxxxxxxxx");
			System.out.println(user);
			System.out.println("xxxxxxxxxxxxx");
			if (Helper.checkAdminRole()) {
				return "redirect:/admin";
			} else {
				return "redirect:/admin/logout";
			}

		} else

		{
			session.setAttribute("msg_2", "Wrong Username or Password...");
			return "redirect:/admin/login";
		}

	}

	@GetMapping("/register")
	public String register() {
		
		
		return "/admin/register";		
	}

	@PostMapping("/register")
	public String register(@ModelAttribute User user) {
		
		user.setRole("USER");
		user.setEntryDate(new Date());
		user.setStatus(1);
		
		userService.createOrUpdateUser(user);
		return "/admin/register";		
	}

	@GetMapping("/forgot-password")
	public String forgotpassword() {
		return "/admin/forgot-password";
	}
	// \\//\\//\\//\\//\\//\\//\\//\\

	@GetMapping("/profile")
	public String profile() {
		
		if(!Helper.checkAdminRole()) {
			return "redirect:/admin/logout";
		}
		
		return "/admin/profile";
	}

	@GetMapping("/edit-profile")
	public String editProfile() {
		if(!Helper.checkAdminRole()) {
			return "redirect:/admin/logout";
		}
		
		return "/admin/profile-edit";
	}

	@GetMapping("/change-password")
	public String changepassword() {
		if(!Helper.checkAdminRole()) {
			return "redirect:/admin/logout";
		}
		
		return "/admin/change-password";
	}

	@GetMapping("/logout")
	public String logout() {

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();

		if (session.getAttribute("uname") != null)
			session.removeAttribute("uname");

		if (session.getAttribute("uid") != null)
			session.removeAttribute("uid");

		if (session.getAttribute("urole") != null)
			session.removeAttribute("urole");

		return "redirect:/admin/login";
	}

}
