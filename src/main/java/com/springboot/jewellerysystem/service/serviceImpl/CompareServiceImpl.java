package com.springboot.jewellerysystem.service.serviceImpl;

import java.util.List; 
import javax.persistence.EntityNotFoundException; 
import org.springframework.beans.factory.annotation.Autowired;
import com.springboot.jewellerysystem.dao.CompareDAO; 
import com.springboot.jewellerysystem.entity.Compare;
import com.springboot.jewellerysystem.entity.Product;
import com.springboot.jewellerysystem.service.CompareService; 
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CompareServiceImpl implements CompareService { 

@Autowired
 private CompareDAO CompareDao;
 
 @Override 
    public List<Compare> getAllCompare() { 
        return CompareDao.findAll(); 
    } 

 @Override 
 public List<Compare> getAllCompareByProduct(Product product) { 
     return CompareDao.findAllByProduct(product); 
 } 
@Override 
  public Compare loadCompareById(Integer id) {
 return CompareDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Compare with ID " + id + " Not Found"));
 }

@Override 
    public Compare createOrUpdateCompare(Compare compare) {
return CompareDao.save(compare);
   }

  @Override
 	    public void removeCompare(Integer id) {
 	        CompareDao.deleteById(id);
 	    }

}
