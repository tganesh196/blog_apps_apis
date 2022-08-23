 package com.BikkadIT.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.blog.paylods.ApiResponse;
import com.BikkadIT.blog.paylods.CategoryDto;
import com.BikkadIT.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

	@Autowired
	private CategoryService categoryservice;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createcategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createCategory = this.categoryservice.createCategory(categoryDto);
		return new ResponseEntity<>(createCategory , HttpStatus.CREATED);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updatecategory(@Valid @RequestBody CategoryDto categoryDto , @PathVariable("catId") Integer catId){
	CategoryDto updateCategory = this.categoryservice.updateCategory(categoryDto, catId);
	return new ResponseEntity<> (updateCategory , HttpStatus.OK);
	
	}

	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deletecategory( @PathVariable("catId") Integer catId){
	  this.categoryservice.deleteCategory(catId);
	return new ResponseEntity<ApiResponse> (new ApiResponse("category is deleted successfully" , true) , HttpStatus.OK);

	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getcategory(@PathVariable("catId") Integer catId){
	        CategoryDto categorydto = this.categoryservice.getCategory(catId);
	return new ResponseEntity<CategoryDto> (categorydto , HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllcategory(){
	        List<CategoryDto> categories = this.categoryservice.getallCategory();
	return  ResponseEntity.ok(categories);
	
	
	}
}
