package com.miniproject.glmp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.miniproject.glmp.entity.Grocery;
import com.miniproject.glmp.repository.GroceryListRepository;

@SpringBootApplication
public class GroceryListManagementAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GroceryListManagementAppApplication.class, args);
	}
    @Autowired
    private GroceryListRepository groceryListRepository;
    
	@Override
	public void run(String... args) throws Exception {
		
		/*
		Grocery groceryItem1 = new Grocery("Tomato" , 2, 240 );
		groceryListRepository.save(groceryItem1);
		
		Grocery groceryItem2 = new Grocery("Onion" , 1, 90 );
		groceryListRepository.save(groceryItem2);
		
		Grocery groceryItem3 = new Grocery("Potato" , 3, 290 );
		groceryListRepository.save(groceryItem3);
		
		Grocery groceryItem4 = new Grocery("Apple" , 1, 100 );
		groceryListRepository.save(groceryItem4); */
		
		
		
				
	}

}
