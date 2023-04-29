package com.springboot.jewellerysystem.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jewellerysystem.dao.ProductDAO;
import com.springboot.jewellerysystem.dao.TodayRateDAO;
import com.springboot.jewellerysystem.entity.Brand;
import com.springboot.jewellerysystem.entity.Category;
import com.springboot.jewellerysystem.entity.Product;
import com.springboot.jewellerysystem.entity.TodayRate;
import com.springboot.jewellerysystem.service.ProductService;
import com.springboot.jewellerysystem.service.TodayRateService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDao;


	
	@Override
	public List<Product> getAllProduct() {
		return productDao.findAll();
	}

	@Override
	public Product loadProductById(Integer id) {
		return productDao.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Product with ID " + id + " Not Found"));
	}

	@Override
	public Product createOrUpdateProduct(Product product) {
		return productDao.save(product);
	}

	@Override
	public void removeProduct(Integer id) {
		productDao.deleteById(id);
	}

	@Override
	public List<Product> getAllProductByCategory(Category category) {

		return productDao.findByCategory(category);
	}

	@Override
	public List<Product> getAllProductByBrand(Brand brand) {

		return productDao.findByBrand(brand);
	}

	@Override
	public List<Product> getAllProductByCategoryAndName(Category category, String name) {
		return productDao.findByCategoryAndNameContaining(category, name);
	}
}
