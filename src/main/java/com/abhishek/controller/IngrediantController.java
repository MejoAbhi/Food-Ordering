package com.abhishek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.entity.IngrediantsCatagory;
import com.abhishek.entity.IngrediantsItem;
import com.abhishek.request.IngrediantCategoryRequests;
import com.abhishek.request.IngrediantRequest;
import com.abhishek.service.IngrediantsService;

@RestController
@RequestMapping("/api/admin/ingrediants")
public class IngrediantController {

	@Autowired
	private IngrediantsService ingrediantsService;
	
	@PostMapping("/category")
	public ResponseEntity<IngrediantsCatagory> createIngrediantCategory(@RequestBody IngrediantCategoryRequests req) throws Exception{
		
		IngrediantsCatagory item=ingrediantsService.createIngrediantCategory(req.getName(), req.getRestaurantId());
		return new ResponseEntity<>(item, HttpStatus.CREATED);
	}
	
	
	@PostMapping("")
	public ResponseEntity<IngrediantsItem> createIngrediantItem(@RequestBody IngrediantRequest req) throws Exception{
		
		IngrediantsItem item=ingrediantsService.createIngrediantItem(req.getRestaurantId(),req.getName(),req.getCategoryId());
		return new ResponseEntity<>(item, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{id}/stoke")
	public ResponseEntity<IngrediantsItem> updateIngrediantStock(@PathVariable Long id) throws Exception{
		
		IngrediantsItem item=ingrediantsService.updateStock(id);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/restaurant/{id}")
	public ResponseEntity<List<IngrediantsItem>> getRestaurantIngrediant(@PathVariable Long id) throws Exception{
		
		List<IngrediantsItem> item=ingrediantsService.findRestaurantIngrediants(id);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
	
	@GetMapping("/restaurant/{id}/category")
	public ResponseEntity<List<IngrediantsCatagory>> getRestaurantIngrediantCategory(@PathVariable Long id) throws Exception{
		
		List<IngrediantsCatagory> item=ingrediantsService.findIngrediantsCatagoryByRestaurantId(id);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
	
}
