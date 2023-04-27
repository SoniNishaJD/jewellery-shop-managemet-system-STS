package com.springboot.jewellerysystem.controller.users;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.jewellerysystem.entity.Banner;
import com.springboot.jewellerysystem.entity.Blog;
import com.springboot.jewellerysystem.entity.BlogCategory;
import com.springboot.jewellerysystem.entity.Cart;
import com.springboot.jewellerysystem.entity.Cart;
import com.springboot.jewellerysystem.entity.Category;
import com.springboot.jewellerysystem.entity.CompanyDetail;
import com.springboot.jewellerysystem.entity.ContactUs;
import com.springboot.jewellerysystem.entity.Faq;
import com.springboot.jewellerysystem.entity.Link;
import com.springboot.jewellerysystem.entity.MainCategory;
import com.springboot.jewellerysystem.entity.OurService;
import com.springboot.jewellerysystem.entity.Product;
import com.springboot.jewellerysystem.entity.User;
import com.springboot.jewellerysystem.entity.Wishlist;
import com.springboot.jewellerysystem.service.BannerService;
import com.springboot.jewellerysystem.service.BlogCategoryService;
import com.springboot.jewellerysystem.service.BlogService;
import com.springboot.jewellerysystem.service.CartService;
import com.springboot.jewellerysystem.service.CategoryService;
import com.springboot.jewellerysystem.service.CompanyDetailService;
import com.springboot.jewellerysystem.service.ContactUsService;
import com.springboot.jewellerysystem.service.FaqService;
import com.springboot.jewellerysystem.service.LinkService;
import com.springboot.jewellerysystem.service.OurServiceService;
import com.springboot.jewellerysystem.service.ProductService;
import com.springboot.jewellerysystem.service.UserService;
import com.springboot.jewellerysystem.service.WishlistService;
import com.springboot.jewellerysystem.util.Helper;

@Controller
public class HomeController {

	private UserService userService;
	private CategoryService categoryService;
	
	private ProductService productService;
	
	private OurServiceService ourServiceService;
	
	private CompanyDetailService companyDetailService;
	
	private ContactUsService contactUsService;
	
	private LinkService linkService;
	
	private FaqService faqService;
	
	private BlogService blogService;
	
	private BlogCategoryService blogCategoryService;
	
	private WishlistService wishlistService;
	private BannerService bannerService;
	private CartService cartService;

	public HomeController(CategoryService categoryService, ProductService productService,
			OurServiceService ourServiceService, CompanyDetailService companyDetailService,
			ContactUsService contactUsService, LinkService linkService, FaqService faqService, BlogService blogService,
			BlogCategoryService blogCategoryService, WishlistService wishlistService,BannerService bannerService,
			CartService cartService,UserService userService
			) {
		super();
		this.categoryService = categoryService;
		this.productService = productService;
		this.ourServiceService = ourServiceService;
		this.companyDetailService = companyDetailService;
		this.contactUsService = contactUsService;
		this.linkService = linkService;
		this.faqService = faqService;
		this.blogService = blogService;
		this.blogCategoryService = blogCategoryService;
		this.wishlistService = wishlistService;
		this.bannerService = bannerService;
		this.cartService = cartService;
		this.userService = userService;
	}
	


	@GetMapping({"/","/home"})
	public String showHome(Model m) {
		
		
		
		
		List<Category> tabCategory1= new ArrayList<>();// =helper.getCategoryList(new int[]{1,2,3,4},categoryService);
		List<Category> tabCategory2= new ArrayList<>();//helper.getCategoryList(new int[]{5,11,12,14},categoryService);
		List<Category> tabCategory3= new ArrayList<>();//helper.getCategoryList(new int[]{7,10,6,8},categoryService);
		Category category = new Category();
		Helper helper = new Helper();
		
		category =categoryService.loadCategoryById(1);		
		tabCategory1.add(category);
		
		category =categoryService.loadCategoryById(2);		
		tabCategory1.add(category);
		
		category =categoryService.loadCategoryById(3);		
		tabCategory1.add(category);
		
		category =categoryService.loadCategoryById(4);		
		tabCategory1.add(category);
		
		//---------------------------
		category =categoryService.loadCategoryById(5);		
		tabCategory2.add(category);
		
		category =categoryService.loadCategoryById(11);		
		tabCategory2.add(category);
		
		category =categoryService.loadCategoryById(12);		
		tabCategory2.add(category);
		
		category =categoryService.loadCategoryById(14);		
		tabCategory2.add(category);
		//-----------------------------
		
		category =categoryService.loadCategoryById(7);		
		tabCategory3.add(category);
		
		category =categoryService.loadCategoryById(10);		
		tabCategory3.add(category);
		
		category =categoryService.loadCategoryById(6);		
		tabCategory3.add(category);
		
		category =categoryService.loadCategoryById(8);		
		tabCategory3.add(category);
		
	   m.addAttribute("tabCategory1",tabCategory1);
	   m.addAttribute("tabCategory2",tabCategory2);
	   m.addAttribute("tabCategory3",tabCategory3);
		
	
	   Banner banner1 = bannerService.loadBannerById(7);//large single
	   Banner banner2 = bannerService.loadBannerById(8);//double
	   Banner banner3 = bannerService.loadBannerById(6);//triple
	   m.addAttribute("banner1",banner1);
	   m.addAttribute("banner2",banner2);
	   m.addAttribute("banner3",banner3);
	   
	   List<Category> listCategory = categoryService.getAllCategoryByMainCategory(new MainCategory(1));
		m.addAttribute("listCategory",listCategory);
		
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
	public String showAbout(Model m) {
		
		
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
	
		return "user/about-us";
	}
	
	@GetMapping("/contact")
	public String showContact(Model m,ContactUs contactUs) {
		
		List<Category> listCategory = categoryService.getAllCategoryByMainCategory(new MainCategory(1));
		m.addAttribute("listCategory",listCategory);
		
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
	
		
		return "user/contact";
	}
	
	@PostMapping("/contact/save")
	public String saveContact(ContactUs contactUs) {
		
		contactUsService.createOrUpdateContactUs(contactUs);
		
		
		return "redirect:/home";
	}
	
	@GetMapping("/faq")
	public String showFaq(Model m) {
		
		List<Faq> listFaq = faqService.getAllFaq();
		m.addAttribute("listFaq", listFaq);
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
		
		return "user/faq";
	}
	
	@GetMapping("/blog")
	public String showBlog(Model m) {
		
		List<Blog> listBlog = blogService.getAllBlog();
		m.addAttribute("listBlog", listBlog);
		
		List<BlogCategory> listBlogCategory = blogCategoryService.getAllBlogCategory();
		m.addAttribute("listBlogCategory", listBlogCategory);
		
		List<Category> listCategory = categoryService.getAllCategoryByMainCategory(new MainCategory(1));
		m.addAttribute("listCategory",listCategory);
		
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
		
		return "user/blog";
	}
	@GetMapping("/blogDetail")
	public String showBlog(@RequestParam int id, Model m) {
		
		Blog blog = blogService.loadBlogById(id);
		m.addAttribute("blog", blog);
		
		List<BlogCategory> listBlogCategory = blogCategoryService.getAllBlogCategory();
		m.addAttribute("listBlogCategory", listBlogCategory);
		
		List<Category> listCategory = categoryService.getAllCategoryByMainCategory(new MainCategory(1));
		m.addAttribute("listCategory",listCategory);
		
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
		
		return "user/blog-detail";
	}
	

	@GetMapping("/cart")
	public String showCart(@RequestParam(value = "del_id",defaultValue = "0") int del_id, @RequestParam(value = "add_id",defaultValue = "0") int add_id,   Model m) {
		if(add_id > 0)
		{
			Product product = productService.loadProductById(add_id);
			User user = userService.loadUserById(1);
			Cart cart = new Cart(user,product,1);
			cartService.createOrUpdateCart(cart);
		}
		if(del_id > 0)
		{
			cartService.removeCart(del_id);
		}
		
		List<Category> listCategory = categoryService.getAllCategoryByMainCategory(new MainCategory(1));
		m.addAttribute("listCategory",listCategory);	
		
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
		
	 	List<Cart> carts =  cartService.getAllCart();
	 	float total=0;
	 	m.addAttribute("carts",carts);
	 	for(int i=0;i<carts.size();i++)
	 	{
	 		total = carts.get(i).getQty()*carts.get(i).getProduct().getSalesPrice();
	 	}
	 	m.addAttribute("total", total);
		return "user/cart";
		
	}
	
	
	
	@GetMapping("/compare")
	public String showCompare() {
		return "user/compare";
	}
	
	@GetMapping(value="/shop")
	public String showShopPage(@RequestParam int cat_id, Model m) {
		
		
		List<Product> productList = productService.getAllProductByCategory(new Category(cat_id));
		m.addAttribute("productList", productList);
		
		List<Category> listCategory = categoryService.getAllCategoryByMainCategory(new MainCategory(1));
		m.addAttribute("listCategory",listCategory);
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
		
		return "user/shop";
	}
	
	@GetMapping("/productDetail")
	public String showProductDetail(@RequestParam("id")int id, Model m ) {
		
		Product product = productService.loadProductById(id);
		m.addAttribute("product",product);
		List<Category> listCategory = categoryService.getAllCategoryByMainCategory(new MainCategory(1));
		m.addAttribute("listCategory",listCategory);
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
		
		return "user/single-product";
	}
	
	@GetMapping("/wishlist")
	public String showWishlist(@RequestParam(value="id",defaultValue = "0")int id, Model m) {
		
		if(id >0) {
			Product product = productService.loadProductById(id);
			User user = userService.loadUserById(1);
			Wishlist wishlist = new Wishlist(product,user, new Date());
		
			wishlistService.createOrUpdateWishlist(wishlist);
		}
		List<Wishlist> listWishlist = wishlistService.getAllWishlist();
		m.addAttribute("listWishlist",listWishlist);
		List<Category> listCategory = categoryService.getAllCategoryByMainCategory(new MainCategory(1));
		m.addAttribute("listCategory",listCategory);
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
		
		return "user/wishlist";
	}
	
	@GetMapping("/checkout")
	public String showCheckout(Model m) {
		
		List<Cart> carts =  cartService.getAllCart();
	 	float total=0;
	 	m.addAttribute("carts",carts);
	 	for(int i=0;i<carts.size();i++)
	 	{
	 		total = carts.get(i).getQty()*carts.get(i).getProduct().getSalesPrice();
	 	}
	 	m.addAttribute("total", total);
	 	List<Category> listCategory = categoryService.getAllCategoryByMainCategory(new MainCategory(1));
		m.addAttribute("listCategory",listCategory);
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
		
		return "user/checkout";
	}
	
	@GetMapping("/profile")
	public String showAccount() {
		return "user/my-account";
	}
	
	

}
