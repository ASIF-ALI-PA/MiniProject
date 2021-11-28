package com.miniproject.glmp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.miniproject.glmp.entity.Grocery;
import com.miniproject.glmp.repository.GroceryListRepository;
import com.miniproject.glmp.service.GroceryListService;

@Service
public class GroceryListServiceImpl implements GroceryListService{
	
	private GroceryListRepository groceryListRepository;

	public GroceryListServiceImpl(GroceryListRepository groceryListRepository) {
		super();
		this.groceryListRepository = groceryListRepository;
	}

	@Override
	public List<Grocery> getAllGrocerys() {
		return groceryListRepository.findAll();
	}

	@Override
	public Grocery saveGrocery(Grocery grocery) {
		return groceryListRepository.save(grocery);
	}

	@Override
	public Grocery getGroceryById(Long id) {
		
		return groceryListRepository.findById(id).get();
	}

	@Override
	public Grocery updateGrocery(Grocery grocery) {
		return groceryListRepository.save(grocery);
	}

	@Override
	public void deleteGroceryItemById(Long id) {
		groceryListRepository.deleteById(id);
		
	}

}
