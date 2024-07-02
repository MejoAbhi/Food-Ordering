package com.abhishek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.entity.Catagory;
import com.abhishek.entity.User;
import com.abhishek.service.CategoryService;
import com.abhishek.service.UserService;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	public CategoryService categoryService;
	
	@Autowired
	public UserService userService;
	@PostMapping("/admin/category")
	public ResponseEntity<Catagory> createCategory(@RequestBody Catagory catagory,
													@RequestHeader("Authorization") String jwt) throws Exception{
		User user=userService.findUSerByJwtToken(jwt);
		Catagory createCategory=categoryService.createCategory(catagory.getName(), catagory.getId());
		return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/category/restaurant")
	public ResponseEntity<List<Catagory>> getRestaurantCategory(
													@RequestHeader("Authorization") String jwt) throws Exception{
		User user=userService.findUSerByJwtToken(jwt);
		
		List<Catagory> category=categoryService.findCategoryByRestaurantId(user.getId());
		return new ResponseEntity<>(category, HttpStatus.CREATED);
		
	}
}
