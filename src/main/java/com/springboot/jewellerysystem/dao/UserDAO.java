package com.springboot.jewellerysystem.dao;

import org.springframework.data.jpa.repository.JpaRepository; 
import com.springboot.jewellerysystem.entity.User;

public interface UserDAO extends JpaRepository<User, Integer> { 
	
User findByEmailAndPassword(String email, String password);

boolean existsByEmail(String email);

} 
