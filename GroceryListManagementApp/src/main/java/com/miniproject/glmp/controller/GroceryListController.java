package com.miniproject.glmp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.miniproject.glmp.entity.Grocery;
import com.miniproject.glmp.service.GroceryListService;

@Controller
public class GroceryListController {
	
	private GroceryListService groceryListService;

	public GroceryListController(GroceryListService groceryListService) {
		super();
		this.groceryListService = groceryListService;
	}
	
	
	//handler method to handle grocery list and return mode and view
	@GetMapping("/grocerys")
	public String listOfGrocery(Model model) {
		model.addAttribute("grocerys",groceryListService.getAllGrocerys());
		return "grocerys";
		
	}
	@GetMapping("/grocerys/new")
	public String createGroceryListForm(Model model) {
		
		// an empty object is created to hold grocery list data
		
		Grocery grocery = new Grocery();
		model.addAttribute("grocery", grocery);
		return "add_grocery";
		
	}
	
	@PostMapping("/grocerys")
	public String addGrocery(@ModelAttribute("grocery") Grocery grocery) {
		groceryListService.saveGrocery(grocery);
		return "redirect:/grocerys";
		
	}
	@GetMapping("/grocerys/edit/{id}")
	public String editGroceryListForm(@PathVariable Long id, Model model) {
		model.addAttribute("grocery" , groceryListService.getGroceryById(id));
		return "edit_grocery";
		
		
	}
	
	@PostMapping("/grocerys/{id}")
	public String updateGroceryList(@PathVariable Long id, 
			@ModelAttribute("grocery") Grocery grocery,
			Model model) {
		//to get grocery by id from database
		
		Grocery currentGroceryItem = groceryListService.getGroceryById(id);
		currentGroceryItem.setId(id);
		currentGroceryItem.setGroceryName(grocery.getGroceryName());
		currentGroceryItem.setQuantity(grocery.getQuantity());
		currentGroceryItem.setPrice(grocery.getPrice());
		
		//save object of updated grocery items 
		groceryListService.updateGrocery(currentGroceryItem);
		return "redirect:/grocerys";
		
		
	}
	// Grocery item delete handling method 
	@GetMapping("/grocerys/{id}")
	public String deleteGroceryItem(@PathVariable Long id) {
		groceryListService.deleteGroceryItemById(id);
		return "redirect:/grocerys";
	}

}
