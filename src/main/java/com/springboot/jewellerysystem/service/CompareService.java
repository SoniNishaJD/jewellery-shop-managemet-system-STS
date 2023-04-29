package com.springboot.jewellerysystem.service;

import com.springboot.jewellerysystem.entity.Compare;
import com.springboot.jewellerysystem.entity.Product;

import java.util.List;

public interface CompareService { 

  List<Compare> getAllCompare();

Compare loadCompareById(Integer id );

Compare createOrUpdateCompare(Compare compare);

void removeCompare(Integer id);

List<Compare> getAllCompareByProduct(Product product);

} 
