package com.miniproject.glmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniproject.glmp.entity.Grocery;


public interface GroceryListRepository extends JpaRepository<Grocery, Long>{
	

}
