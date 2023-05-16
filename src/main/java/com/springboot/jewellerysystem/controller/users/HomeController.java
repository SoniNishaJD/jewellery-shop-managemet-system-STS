package com.springboot.jewellerysystem.controller.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.springboot.jewellerysystem.entity.Banner;
import com.springboot.jewellerysystem.entity.Blog;
import com.springboot.jewellerysystem.entity.BlogCategory;
import com.springboot.jewellerysystem.entity.Brand;
import com.springboot.jewellerysystem.entity.Cart;
import com.springboot.jewellerysystem.entity.Category;
import com.springboot.jewellerysystem.entity.CompanyDetail;
import com.springboot.jewellerysystem.entity.Compare;
import com.springboot.jewellerysystem.entity.ContactUs;
import com.springboot.jewellerysystem.entity.Country;
import com.springboot.jewellerysystem.entity.Faq;
import com.springboot.jewellerysystem.entity.Link;
import com.springboot.jewellerysystem.entity.MainCategory;
import com.springboot.jewellerysystem.entity.Newsletter;
import com.springboot.jewellerysystem.entity.Order;
import com.springboot.jewellerysystem.entity.OurService;
import com.springboot.jewellerysystem.entity.Product;
import com.springboot.jewellerysystem.entity.TodayRate;
import com.springboot.jewellerysystem.entity.User;
import com.springboot.jewellerysystem.entity.Wishlist;
import com.springboot.jewellerysystem.service.BannerService;
import com.springboot.jewellerysystem.service.BlogCategoryService;
import com.springboot.jewellerysystem.service.BlogService;
import com.springboot.jewellerysystem.service.BrandService;
import com.springboot.jewellerysystem.service.CartService;
import com.springboot.jewellerysystem.service.CategoryService;
import com.springboot.jewellerysystem.service.CompanyDetailService;
import com.springboot.jewellerysystem.service.CompareService;
import com.springboot.jewellerysystem.service.ContactUsService;
import com.springboot.jewellerysystem.service.CountryService;
import com.springboot.jewellerysystem.service.FaqService;
import com.springboot.jewellerysystem.service.LinkService;
import com.springboot.jewellerysystem.service.NewsletterService;
import com.springboot.jewellerysystem.service.OrderService;
import com.springboot.jewellerysystem.service.OurServiceService;
import com.springboot.jewellerysystem.service.ProductService;
import com.springboot.jewellerysystem.service.TodayRateService;
import com.springboot.jewellerysystem.service.UserService;
import com.springboot.jewellerysystem.service.WishlistService;
import com.springboot.jewellerysystem.util.Helper;

@Controller
public class HomeController {

	private OrderService orderService;
	private NewsletterService newsletterService;
	private UserService userService;
	private CategoryService categoryService;

	private CountryService countryService;
	
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
	private BrandService brandService;
	private CompareService compareService;
	private TodayRateService todayRateService;
	
	
	public HomeController(UserService userService, CategoryService categoryService, ProductService productService,
			CountryService countryService,
			OrderService orderService,
			NewsletterService newsletterService,
			OurServiceService ourServiceService, CompanyDetailService companyDetailService,
			ContactUsService contactUsService, LinkService linkService, FaqService faqService, BlogService blogService,
			BlogCategoryService blogCategoryService, WishlistService wishlistService, BannerService bannerService,
			CartService cartService, BrandService brandService, CompareService compareService, TodayRateService todayRateService) {
		super();
		this.orderService = orderService;
		this.newsletterService = newsletterService;
		this.userService = userService;
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
		this.brandService = brandService;
		this.compareService = compareService;
		this.todayRateService = todayRateService;
		this.countryService = countryService;
	}



	@GetMapping({"/","/home"})
	public String showHome(Model m) {
//		//\\//\\//\\
//		List<Product> xlist = productService.getAllProduct();
//		for(Product p : xlist) {
//			TodayRate  rt= todayRateService.loadTodayRateById(1);
//			p.setSalesPrice(p.getGroseWeight()*rt.getPrice()/10);
//			productService.createOrUpdateProduct(p);
//		}
//		//\\//\\//\\
		
		List<Category> tabCategory1= new ArrayList<>();//helper.getCategoryList(new int[]{1,2,3,4},categoryService);
		List<Category> tabCategory2= new ArrayList<>();//helper.getCategoryList(new int[]{5,11,12,14},categoryService);
		List<Category> tabCategory3= new ArrayList<>();//helper.getCategoryList(new int[]{7,10,6,8},categoryService);
		Category category = new Category();
		
		
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
		
		List<Brand> listBrand = brandService.getAllBrand();
		m.addAttribute("listBrand" , listBrand);
		
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
	
		List<TodayRate> todayRates = todayRateService.getAllTodayRate();
		m.addAttribute("todayRates", todayRates);
		
		List<Cart> carts =  cartService.getAllCart();
	 	m.addAttribute("carts",carts);
	 	float total = 0;
		for(int i=0;i<carts.size();i++)
	 	{
	 		total += carts.get(i).getQty()*carts.get(i).getProduct().getSalesPrice();
	 	}
	 	m.addAttribute("total", total);
	 	
	 		 	
		return "user/index";
	}
	
	@GetMapping("/about")
	public String showAbout(Model m) {
		
		
		List<Category> listCategory = categoryService.getAllCategoryByMainCategory(new MainCategory(1));
		m.addAttribute("listCategory",listCategory);
		
		List<OurService> listOurService = ourServiceService.getAllOurService();
		m.addAttribute("listOurService",listOurService);
		
		List<Category> listCategory2 = categoryService.getAllCategory().subList(0, 10);
		m.addAttribute("listCategory2",listCategory2);
		
		List<Link> listLink = linkService.getAllLink();
		m.addAttribute("listLink" , listLink);
		
		List<Brand> listBrand = brandService.getAllBrand();
		m.addAttribute("listBrand" , listBrand);
		
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
	
		List<TodayRate> todayRates = todayRateService.getAllTodayRate();
		m.addAttribute("todayRates", todayRates);
		
		List<Cart> carts =  cartService.getAllCart();
	 	m.addAttribute("carts",carts);
	 	float total = 0;
		for(int i=0;i<carts.size();i++)
	 	{
	 		total += carts.get(i).getQty()*carts.get(i).getProduct().getSalesPrice();
	 	}
	 	m.addAttribute("total", total);
	 	
	
		return "user/about-us";
	}
	@GetMapping("/change-password")
	public String changepassword1(){
		if(!Helper.checkUserRole()) {
			return "redirect:/login";
		}

		return "/user/change-password";

}
	@PostMapping("/change-password")
	public String changepassword2(@RequestParam("password")String password) {
		if(!Helper.checkUserRole()) {
			return "redirect:/login";
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
		
		return "redirect:/";
	}

	
	
	@GetMapping("/contact")
	public String showContact(Model m,ContactUs contactUs) {
		
		List<Category> listCategory = categoryService.getAllCategoryByMainCategory(new MainCategory(1));
		m.addAttribute("listCategory",listCategory);
		
		List<OurService> listOurService = ourServiceService.getAllOurService();
		m.addAttribute("listOurService",listOurService);
		
		List<Category> listCategory2 = categoryService.getAllCategory().subList(0, 10);
		m.addAttribute("listCategory2",listCategory2);
		
		List<Link> listLink = linkService.getAllLink();
		m.addAttribute("listLink" , listLink);
		
		List<Brand> listBrand = brandService.getAllBrand();
		m.addAttribute("listBrand" , listBrand);
		
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
	
		List<TodayRate> todayRates = todayRateService.getAllTodayRate();
		m.addAttribute("todayRates", todayRates);
		
		List<Cart> carts =  cartService.getAllCart();
	 	m.addAttribute("carts",carts);
	 	float total = 0;
		for(int i=0;i<carts.size();i++)
	 	{
	 		total += carts.get(i).getQty()*carts.get(i).getProduct().getSalesPrice();
	 	}
	 	m.addAttribute("total", total);
	 	
	
		
		return "user/contact";
	}
	
	@PostMapping("/contact/save")
	public String saveContact(ContactUs contactUs,HttpSession session ) {
		
		
		ContactUs contactUs2 =  contactUsService.createOrUpdateContactUs(contactUs);
		if(contactUs2 != null) {
			session.setAttribute("msg", "Contact submitted successfully...");
		}
		
		return "redirect:/contact";
	}
	
	@GetMapping("/faq")
	public String showFaq(Model m) {
		
		List<Faq> listFaq = faqService.getAllFaq();
		m.addAttribute("listFaq", listFaq);
		List<Category> listCategory = categoryService.getAllCategoryByMainCategory(new MainCategory(1));
		m.addAttribute("listCategory",listCategory);
		
		List<OurService> listOurService = ourServiceService.getAllOurService();
		m.addAttribute("listOurService",listOurService);
		
		List<Category> listCategory2 = categoryService.getAllCategory().subList(0, 10);
		m.addAttribute("listCategory2",listCategory2);
		
		List<Link> listLink = linkService.getAllLink();
		m.addAttribute("listLink" , listLink);
		
		List<Brand> listBrand = brandService.getAllBrand();
		m.addAttribute("listBrand" , listBrand);
		
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
	
		List<TodayRate> todayRates = todayRateService.getAllTodayRate();
		m.addAttribute("todayRates", todayRates);
		
		List<Cart> carts =  cartService.getAllCart();
	 	m.addAttribute("carts",carts);
	 	float total = 0;
		for(int i=0;i<carts.size();i++)
	 	{
	 		total += carts.get(i).getQty()*carts.get(i).getProduct().getSalesPrice();
	 	}
	 	m.addAttribute("total", total);
	 	
		
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
		
		List<OurService> listOurService = ourServiceService.getAllOurService();
		m.addAttribute("listOurService",listOurService);
		
		List<Category> listCategory2 = categoryService.getAllCategory().subList(0, 10);
		m.addAttribute("listCategory2",listCategory2);
		
		List<Link> listLink = linkService.getAllLink();
		m.addAttribute("listLink" , listLink);
		
		List<Brand> listBrand = brandService.getAllBrand();
		m.addAttribute("listBrand" , listBrand);
		
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
	
		List<TodayRate> todayRates = todayRateService.getAllTodayRate();
		m.addAttribute("todayRates", todayRates);
		
		List<Cart> carts =  cartService.getAllCart();
	 	m.addAttribute("carts",carts);
	 	float total = 0;
		for(int i=0;i<carts.size();i++)
	 	{
	 		total += carts.get(i).getQty()*carts.get(i).getProduct().getSalesPrice();
	 	}
	 	m.addAttribute("total", total);
	 	
		
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
		
		List<OurService> listOurService = ourServiceService.getAllOurService();
		m.addAttribute("listOurService",listOurService);
		
		List<Category> listCategory2 = categoryService.getAllCategory().subList(0, 10);
		m.addAttribute("listCategory2",listCategory2);
		
		List<Link> listLink = linkService.getAllLink();
		m.addAttribute("listLink" , listLink);
		
		List<Brand> listBrand = brandService.getAllBrand();
		m.addAttribute("listBrand" , listBrand);
		
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
	
		List<TodayRate> todayRates = todayRateService.getAllTodayRate();
		m.addAttribute("todayRates", todayRates);
		
		List<Cart> carts =  cartService.getAllCart();
	 	m.addAttribute("carts",carts);
	 	float total = 0;
		for(int i=0;i<carts.size();i++)
	 	{
	 		total += carts.get(i).getQty()*carts.get(i).getProduct().getSalesPrice();
	 	}
	 	m.addAttribute("total", total);
	 	
		
		return "user/blog-detail";
	}
	

	@GetMapping("/cart")
	public String showCart(@RequestParam(value = "del_id",defaultValue = "0") int del_id, @RequestParam(value = "add_id",defaultValue = "0") int add_id,   Model m) {
		
		if(!Helper.checkUserRole() && !Helper.checkAdminRole()) {return "redirect:/logout";}
		
		
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
		
		List<OurService> listOurService = ourServiceService.getAllOurService();
		m.addAttribute("listOurService",listOurService);
		
		List<Category> listCategory2 = categoryService.getAllCategory().subList(0, 10);
		m.addAttribute("listCategory2",listCategory2);
		
		List<Link> listLink = linkService.getAllLink();
		m.addAttribute("listLink" , listLink);
		
		List<Brand> listBrand = brandService.getAllBrand();
		m.addAttribute("listBrand" , listBrand);
		
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
	
		List<TodayRate> todayRates = todayRateService.getAllTodayRate();
		m.addAttribute("todayRates", todayRates);
		
		List<Cart> carts =  cartService.getAllCart();
	 	m.addAttribute("carts",carts);
	 	float total = 0;
		for(int i=0;i<carts.size();i++)
	 	{
	 		total += carts.get(i).getQty()*carts.get(i).getProduct().getSalesPrice();
	 	}
	 	m.addAttribute("total", total);
	 	
		return "user/cart";
		
	}
	@GetMapping("/register")
	public String register() {
		
		
		return "/user/register";		
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
	
	@GetMapping("/login")
	public String login() {
		
		return "/user/login";		
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
			
			if (Helper.checkUserRole()) {
				session.setAttribute("msg", "You are successfully Login..");
				return "redirect:/home";
			} else {
				session.setAttribute("msg", "Something went Wrong.");
				return "redirect:/logout";
			}

		} else

		{
			session.setAttribute("msg", "Wrong Username or Password...");
			return "redirect:/login";
		}

	}
	@GetMapping("/login-register")
	public String login_register() {	
		
		return "/user/login-register";		
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

		session.setAttribute("msg", "You are successfully log-out from system..");
		return "redirect:/";
	}
	
	
	@GetMapping("/compare")
	public String showCompare(@RequestParam(value="add_id",defaultValue = "0")int add_id,@RequestParam(value="del_id",defaultValue = "0")int del_id, Model m) {
		if(add_id >0) {
			Product product = productService.loadProductById(add_id);
			User user = userService.loadUserById(1);
			if(compareService.getAllCompareByProduct(product).size() <= 0) {
			Compare compare = new Compare(product,user);	
			
			compareService.createOrUpdateCompare(compare);
			}
		}
		if(del_id >0) {
					
			compareService.removeCompare(del_id);
		}
		List<Compare> compares = compareService.getAllCompare();
		m.addAttribute("compares",compares);
		List<Category> listCategory = categoryService.getAllCategoryByMainCategory(new MainCategory(1));
		m.addAttribute("listCategory",listCategory);
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
		
		return "user/compare";
	}
	

	@GetMapping(value="/shop")
	public String showShopPage(@RequestParam (value = "search",defaultValue = "")String search, @RequestParam (value = "cat_id",defaultValue = "0")int cat_id,@RequestParam(value = "brand_id",defaultValue = "0") int brand_id, Model m) {
		List<Product> productList  = new ArrayList<>();
		if(cat_id >0) {
		 //productList = productService.getAllProductByCategory(new Category(cat_id));
		 productList = productService.getAllProductByCategoryAndName(new Category(cat_id),search);
		}else if(brand_id>0) {
			 productList = productService.getAllProductByBrand(new Brand(brand_id));
		}else {
			productList = productService.getAllProduct();
		}
		
		m.addAttribute("productList", productList);
		
		List<Category> listCategory = categoryService.getAllCategoryByMainCategory(new MainCategory(1));
		m.addAttribute("listCategory",listCategory);
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
		
		List<Brand> listBrand = brandService.getAllBrand();
		m.addAttribute("listBrand" , listBrand);
		
		return "user/shop";
	}
	
	
	@PostMapping(value="/shop")
	public String showShopSearchPage(@RequestParam (value = "search",defaultValue = "")String search, @RequestParam (value = "cat_id",defaultValue = "0")int cat_id,@RequestParam(value = "brand_id",defaultValue = "0") int brand_id, Model m) {
		List<Product> productList  = new ArrayList<>();
		if(cat_id >0) {
		 productList = productService.getAllProductByCategoryAndName(new Category(cat_id),search);
		}else {
			productList = productService.getAllProduct();
		}
		
		
		List<Category> listCategory = categoryService.getAllCategoryByMainCategory(new MainCategory(1));
		m.addAttribute("listCategory",listCategory);
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
		
		List<Brand> listBrand = brandService.getAllBrand();
		m.addAttribute("listBrand" , listBrand);
		
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
	public String showWishlist(@RequestParam(value="add_id",defaultValue = "0")int add_id,@RequestParam(value="del_id",defaultValue = "0")int del_id, Model m) {
		
		if(add_id >0) {
			Product product = productService.loadProductById(add_id);
			User user = userService.loadUserById(1);
			Wishlist wishlist = new Wishlist(product,user, new Date());
			
			wishlistService.createOrUpdateWishlist(wishlist);
		}
		if(del_id >0) {
					
			wishlistService.removeWishlist(del_id);
		}
		List<Wishlist> wishlists = wishlistService.getAllWishlist();
		m.addAttribute("wishlists",wishlists);
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
	 		total += carts.get(i).getQty()*carts.get(i).getProduct().getSalesPrice();
	 	}
	 	m.addAttribute("total", total);
	 	List<Category> listCategory = categoryService.getAllCategoryByMainCategory(new MainCategory(1));
		m.addAttribute("listCategory",listCategory);
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
		
		List<Country> countryList = countryService.getAllCountry();
		m.addAttribute("countryList",countryList);
		
		return "user/checkout";
	}
	@PostMapping("/checkout")
	public String submitCheckout(@ModelAttribute("Order")Order order,Model m,HttpSession session1  ) {
		
		
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		int uid =0;
		if (session.getAttribute("uid") != null)
		{ uid = (int)session.getAttribute("uid");}
		User user = userService.loadUserById(uid);
		
		List<Cart> carts =  cartService.getAllCart();
	 	float total=0;
	 	
	 	for(int i=0;i<carts.size();i++)
	 	{
	 		Cart cart =  carts.get(i);	 		
	 		total += cart.getQty()*carts.get(i).getProduct().getSalesPrice();
	 	}
	 	
	 	
	 	order.setOrderDate(new Date());
	 	order.setUser(user);
	 	order.setFirstName(order.getFirstName());
	 	order.setLastName(order.getLastName());
	 	//order.setAddress("");
	 	//order.setCity("");
	 	//order.setPincode("");
	 	order.setOrderNum("1111");
	 	order.setTrackingCode("1111");
	 	order.setDeliveryStatus("Pending");
	 	order.setPaymentType(order.getPaymentType());
	 	order.setPaymentStatus(0);
	 	order.setPaymentDetail(order.getPaymentType());
	 	order.setGrandTotal(total);
	 	order.setDiscount(0f);
	 	
	 	Order order1 = orderService.createOrUpdateOrder(order);
	 	if(order1 != null) {
	 	
	 		for(int i=0;i<carts.size();i++)
		 	{
		 		Cart cart =  carts.get(i);	 		
		 		cartService.removeCart(cart.getId());
		 	}	
	 		
	 	order1.setOrderNum(Helper.getOrderNo(order1.getId()));
	 	order1.setTrackingCode(Helper.getOrderNo(order1.getId()));
	 	
	 	orderService.createOrUpdateOrder(order1);
	 	
	 	session1.setAttribute("order_no", order1.getOrderNum());	 	
		return "user/order-submit";
	 	}
	 	else {
	 		session1.setAttribute("msg", "Error in Submitting Order");
	 		return "user/checkout";
	 	}
	}

	@GetMapping("/log-out")
	public String logoutForm() {
		
		return "/user/logout";
	}
	
	@GetMapping("/myAccount")
	public String showAccount(Model m) {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		int id = (int)session.getAttribute("uid");
		User user = userService.loadUserById(id);		
		m.addAttribute("user",user);
		
		List<Category> listCategory = categoryService.getAllCategoryByMainCategory(new MainCategory(1));
		m.addAttribute("listCategory",listCategory);
		CompanyDetail companyDetail = companyDetailService.getAllCompanyDetail().get(0);
		m.addAttribute("companyDetail", companyDetail);
		
		return "user/my-account";
	}
	
	@PostMapping("/newsletter")
	public String newsletter(@RequestParam("email")String email) {
		Newsletter newsletter = new Newsletter();
		newsletter.setEmail(email);
		newsletter.setStatus(1);
		newsletterService.createOrUpdateNewsletter(newsletter);
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		session.setAttribute("newsletter", "submitted");
		return "redirect:/";
	}
	
	
}
