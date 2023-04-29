package com.springboot.jewellerysystem.service;

import com.springboot.jewellerysystem.entity.Brand;
import com.springboot.jewellerysystem.entity.Category;
import com.springboot.jewellerysystem.entity.MainCategory;
import com.springboot.jewellerysystem.entity.Product;
import java.util.List;

public interface ProductService {

	List<Product> getAllProduct();

	Product loadProductById(Integer id);

	Product createOrUpdateProduct(Product product);

	void removeProduct(Integer id);
	
	List<Product> getAllProductByCategory(Category category);
	List<Product> getAllProductByCategoryAndName(Category category,String name);

	List<Product> getAllProductByBrand(Brand brand);
}
