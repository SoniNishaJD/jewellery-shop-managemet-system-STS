package com.springboot.jewellerysystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jewellerysystem.entity.Compare;
import com.springboot.jewellerysystem.entity.Product;

public interface CompareDAO extends JpaRepository<Compare, Integer> { 


List<Compare> findAllByProduct(Product product); 


} 
