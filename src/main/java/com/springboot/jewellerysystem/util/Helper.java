package com.springboot.jewellerysystem.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.springboot.jewellerysystem.entity.Category;
import com.springboot.jewellerysystem.entity.Product;

public class Helper {
	
	
	public Category getCategoryList(Category category){
		
		List<Product> product_list = new ArrayList<>();
		
		for (Iterator iterator2 = category.getProducts().iterator(); iterator2.hasNext();) {
			Product p = (Product) iterator2.next();
			product_list.add(p);
		System.out.println(p.toString());
		}
		
		if(product_list.size() >=10)
		{	product_list= product_list.subList(0, 10);}
		
		Set<Product> product_set = new HashSet<>();
		product_set.addAll(product_list);
		category.setProducts(product_set);
		
		
				
		return category;
		 
	}
	
	public static boolean checkAdminRole() {
		String role="";
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		
		if(session.getAttribute("urole") != null) {
			role = session.getAttribute("urole").toString();
		}
		return role.equalsIgnoreCase("ADMIN");
	}
	public static boolean checkUserRole() {
		String role="";
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		
		if(session.getAttribute("urole") != null) {
			role = session.getAttribute("urole").toString();
		}
		return role.equalsIgnoreCase("CUSTOMER");
	}
	public static String getOrderNo(int id) {
		String pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		
		String ids = String.valueOf(10000+id);
			
		return date+"-"+ids;
		
	}

}
