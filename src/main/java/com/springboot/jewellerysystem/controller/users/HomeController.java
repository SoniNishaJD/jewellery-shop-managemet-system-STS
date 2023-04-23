package com.springboot.jewellerysystem.controller.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.jewellerysystem.entity.Category;
import com.springboot.jewellerysystem.entity.CompanyDetail;
import com.springboot.jewellerysystem.entity.ContactUs;
import com.springboot.jewellerysystem.entity.Link;
import com.springboot.jewellerysystem.entity.MainCategory;
import com.springboot.jewellerysystem.entity.OurService;
import com.springboot.jewellerysystem.entity.Product;
import com.springboot.jewellerysystem.service.CategoryService;
import com.springboot.jewellerysystem.service.CompanyDetailService;
import com.springboot.jewellerysystem.service.ContactUsService;
import com.springboot.jewellerysystem.service.LinkService;
import com.springboot.jewellerysystem.service.OurServiceService;
import com.springboot.jewellerysystem.service.ProductService;

@Controller
public class HomeController {
	
	private CategoryService categoryService;
	
	private ProductService productService;
	
	private OurServiceService ourServiceService;
	
	private CompanyDetailService companyDetailService;
	
	private ContactUsService contactUsService;
	
	private LinkService linkService;

	public HomeController(CategoryService categoryService, ProductService productService,
			OurServiceService ourServiceService, CompanyDetailService companyDetailService,
			ContactUsService contactUsService, LinkService linkService) {
		super();
		this.categoryService = categoryService;
		this.productService = productService;
		this.ourServiceService = ourServiceService;
		this.companyDetailService = companyDetailService;
		this.contactUsService = contactUsService;
		this.linkService = linkService;
	}





	@GetMapping("/")
	public String showHome(Model m) {
		
		List<Category> listCategory = categoryService.getAllCategoryByMainCategory(new MainCategory(1));
		m.addAttribute("listCategory",listCategory);
		List<Category> tabCategory1=new ArrayList<>(); // = categoryService.getAllCategoryByMainCategory(new MainCategory(1)).subList(5, );
		List<Category> tabCategory2=new ArrayList<>();
		List<Category> tabCategory3=new ArrayList<>();
		
		int j=1;
	for(int i=0;i<listCategory.size();i++) {
		if(listCategory.get(i).getProducts().size() > 10 )
		{ 
			if(j<=4){tabCategory1.add(listCategory.get(i));}
			else if(j>=5 && j<=8){tabCategory2.add(listCategory.get(i));}
			else if(j>=9 && j<=12){tabCategory3.add(listCategory.get(i));}
			
			j++;
		}
		
		
	   }
	
	
	   m.addAttribute("tabCategory1",tabCategory1);
	   m.addAttribute("tabCategory2",tabCategory2);
	   m.addAttribute("tabCategory3",tabCategory3);
		
		List<Product> listProduct1 = productService.getAllProductByCategory(tabCategory1.get(0));
		int L1 = (listProduct1.size()>10) ? 10 : listProduct1.size();
		listProduct1 = listProduct1.subList(1, L1);
		m.addAttribute("listProduct1",listProduct1);
		
		List<Product> listProduct2 = productService.getAllProductByCategory(tabCategory1.get(1));
		int L2 = (listProduct2.size()>10) ? 10 : listProduct2.size();
		listProduct2 = listProduct2.subList(1, L2);
		
		m.addAttribute("listProduct2",listProduct2);
		
		List<Product> listProduct3 = productService.getAllProductByCategory(tabCategory1.get(2));
		int L3 = (listProduct3.size()>10) ? 10 : listProduct3.size();
		listProduct3 = listProduct3.subList(1, L3);
		m.addAttribute("listProduct3",listProduct3);
		
		List<Product> listProduct4 = productService.getAllProductByCategory(tabCategory1.get(3));
		int L4 = (listProduct4.size()>10) ? 10 : listProduct4.size();
		listProduct4 = listProduct4.subList(1, L4);
		m.addAttribute("listProduct4",listProduct4);
		
		
		
//		List<Product> listProduct5 = productService.getAllProductByCategory(tabCategory2.get(0));
//		int L5 = (listProduct5.size()>10) ? 10 : listProduct5.size();
//		listProduct5 = listProduct1.subList(1, L5);
//		m.addAttribute("listProduct5",listProduct5);
//		
//		List<Product> listProduct6 = productService.getAllProductByCategory(tabCategory2.get(1));
//		int L6 = (listProduct6.size()>10) ? 10 : listProduct6.size();
//		listProduct6 = listProduct6.subList(1, L6);
//		
//		m.addAttribute("listProduct6",listProduct6);
//		
//		List<Product> listProduct7 = productService.getAllProductByCategory(tabCategory2.get(2));
//		int L7 = (listProduct7.size()>10) ? 10 : listProduct7.size();
//		listProduct7 = listProduct7.subList(1, L7);
//		m.addAttribute("listProduct7",listProduct7);
//		
//		List<Product> listProduct8 = productService.getAllProductByCategory(tabCategory2.get(3));
//		int L8 = (listProduct8.size()>10) ? 10 : listProduct8.size();
//		listProduct8 = listProduct8.subList(1, L8);
//		m.addAttribute("listProduct8",listProduct8);
		

//		List<Product> listProduct9= productService.getAllProductByCategory(tabCategory3.get(0));
//		int L9 = (listProduct9.size()>10) ? 10 : listProduct9.size();
//		listProduct9 = listProduct9.subList(1, L9);
//		
//		m.addAttribute("listProduct9",listProduct9);
//		
//		List<Product> listProduct10 = productService.getAllProductByCategory(tabCategory3.get(2));
//		int L10 = (listProduct7.size()>10) ? 10 : listProduct7.size();
//		listProduct7 = listProduct7.subList(1, L7);
//		m.addAttribute("listProduct7",listProduct7);
//		
//		List<Product> listProduct11 = productService.getAllProductByCategory(tabCategory3.get(3));
//		int L11 = (listProduct8.size()>10) ? 10 : listProduct8.size();
//		listProduct8 = listProduct8.subList(1, L8);
//		m.addAttribute("listProduct8",listProduct8);
//		
//
//		List<Product> listProduct12 = productService.getAllProductByCategory(tabCategory3.get(3));
//		int L12 = (listProduct12.size()>10) ? 10 : listProduct12.size();
//		listProduct8 = listProduct8.subList(1, L8);
//		m.addAttribute("listProduct8",listProduct8);
		
		List<OurService> listOurService = ourServiceService.getAllOurService();
		m.addAttribute("listOurService",listOurService);
		
		List<Category> listCategory2 = categoryService.getAllCategory().subList(0, 10);
		m.addAttribute("listCategory2",listCategory2);
		
		List<Link> listLink = linkService.getAllLink();
		m.addAttribute("listLink" , listLink);
		
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
	
		
		return "user/index";
	}
	
	@GetMapping("/about")
	public String showAbout() {
		return "user/about-us";
	}
	
	@GetMapping("/contact")
	public String showContact(Model m,ContactUs contactUs) {
		
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
	
		
		return "user/contact";
	}
	
	@PostMapping("/contact/save")
	public String saveContact(ContactUs contactUs) {
		
		contactUsService.createOrUpdateContactUs(contactUs);
		
		
		return "redirect:/user/index";
	}
	
	@GetMapping("/cart")
	public String showCart() {
		return "user/cart";
	}
	
	@GetMapping("/compare")
	public String showCompare() {
		return "user/compare";
	}
	
	@GetMapping("/shop")
	public String showShopPage() {
		return "user/shop";
	}
	
	@GetMapping("/productDetail")
	public String showProductDetail() {
		return "user/single-product";
	}
	
	@GetMapping("/wishlist")
	public String showWishlist() {
		return "user/wishlist";
	}
	
	@GetMapping("/checkout")
	public String showCheckout() {
		return "user/checkout";
	}
	
	@GetMapping("/profile")
	public String showAccount() {
		return "user/my-account";
	}
	
	

}
