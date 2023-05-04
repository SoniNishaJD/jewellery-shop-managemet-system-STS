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
import com.springboot.jewellerysystem.service.CitiesService;
import com.springboot.jewellerysystem.service.ContactUsService;
import com.springboot.jewellerysystem.service.MainCategoryService;
import com.springboot.jewellerysystem.service.OrderService;
import com.springboot.jewellerysystem.service.ProductService;
import com.springboot.jewellerysystem.service.StateService;
import com.springboot.jewellerysystem.service.TodayRateService;
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
	TodayRateService todayRateService;
	ContactUsService contactUsService;
	StateService stateService;
	CitiesService citiesService;

	public AuthController(UserService userService, ProductService productService, OrderService orderService,
			MainCategoryService mainCategoryService, CategoryService categoryService, BrandService brandService,TodayRateService todayRateService,ContactUsService contactUsService,StateService stateService,CitiesService citiesService) {
		super();
		this.productService = productService;
		this.orderService = orderService;
		this.mainCategoryService = mainCategoryService;
		this.categoryService = categoryService;
		this.brandService = brandService;
		this.userService = userService;
		this.todayRateService = todayRateService;
		this.contactUsService = contactUsService;
		this.stateService = stateService;
		this.citiesService = citiesService;
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
		float t_rate = todayRateService.loadTodayRateById(1).getPrice();
		int cu_count = contactUsService.getAllContactUs().size();
		int s_count = stateService.getAllState().size();
		int ct_count = citiesService.getAllCities().size();

		m.addAttribute("product_count", p_count);
		m.addAttribute("order_count", o_count);
		m.addAttribute("mainCategory_count", m_count);
		m.addAttribute("category_count", c_count);
		m.addAttribute("brand_count", b_count);
		m.addAttribute("todayRate_rate", t_rate);
		m.addAttribute("contactUs_count", cu_count);
		m.addAttribute("state_count", s_count);
		m.addAttribute("cities_count", ct_count);

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
	public String register(@ModelAttribute User user,HttpSession session ) {
		
		if(userService.checkEmailExist(user.getEmail()))
		{
			session.setAttribute("msg", "Email Already exist..");
			
		}else {
		user.setRole("USER");
		user.setEntryDate(new Date());
		user.setStatus(1);		
		session.setAttribute("msg", "Registration Completed successfully..");
		userService.createOrUpdateUser(user);
		}
		return "/admin/register";		
	}

	@GetMapping("/forgot-password")
	public String forgotpassword() {		
		
		return "/admin/forgot-password";
		
	}
	// \\//\\//\\//\\//\\//\\//\\//\\

	@GetMapping("/profile")
	public String profile(Model m) {
		
		if(!Helper.checkAdminRole()) {
			return "redirect:/admin/logout";
		}
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		int uid = 0;
		if(session.getAttribute("uid") != null) {
			uid = (int)session.getAttribute("uid");
		} 
		User user = userService.loadUserById(uid);
		m.addAttribute("user",user);
		
		
		return "/admin/profile";
	}

	@GetMapping("/edit-profile")
	public String editProfile(Model m) {
		if(!Helper.checkAdminRole()) {
			return "redirect:/admin/logout";
		}
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		int uid = 0;
		if(session.getAttribute("uid") != null) {
			uid = (int)session.getAttribute("uid");
		} 
		User user = userService.loadUserById(uid);
		m.addAttribute("user",user);
		
		return "/admin/profile-edit";
	}
	@PostMapping("/edit-profile")
	public String updateProfile(User user) {
		if(!Helper.checkAdminRole()) {
			return "redirect:/admin/logout";
		}
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		int uid = 0;
		if(session.getAttribute("uid") != null) {
			uid = (int)session.getAttribute("uid");
			User user2 = userService.loadUserById(uid);
			user2.setFirstName(user.getFirstName());
			user2.setLastName(user.getLastName());
			user2.setEmail(user.getEmail());
			user2.setPhone(user.getPhone());
			userService.createOrUpdateUser(user2);
		} 
		
		session.setAttribute("msg", "Profile Updated successfully..");
		
		return "/admin";
	}

	@GetMapping("/change-password")
	public String changepassword(@RequestParam("password")String password) {
		if(!Helper.checkAdminRole()) {
			return "redirect:/admin/logout";
		}
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		int uid = 0;
		if(session.getAttribute("uid") != null) {
			uid = (int)session.getAttribute("uid");
			User user2 = userService.loadUserById(uid);
			user2.setPassword(password);
			userService.createOrUpdateUser(user2);
		} 
		
		session.setAttribute("msg", "Password change successfully..");
		
		return "/admin";
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
	@GetMapping("/log-out")
	public String logoutForm() {
		
		return "/admin/logout";
	}

}
