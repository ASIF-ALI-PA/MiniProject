package com.miniproject.glmp.service;

import java.util.List;

import com.miniproject.glmp.entity.Grocery;

public interface GroceryListService {
	List<Grocery> getAllGrocerys();
	
	Grocery saveGrocery(Grocery grocery);
	
	Grocery getGroceryById(Long id);
	
	Grocery updateGrocery(Grocery grocery);
	
	void deleteGroceryItemById(Long id);

}
