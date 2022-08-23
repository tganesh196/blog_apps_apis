package com.BikkadIT.blog.services;

import java.util.List;

import com.BikkadIT.blog.paylods.CategoryDto;

public interface CategoryService {

	public CategoryDto createCategory(CategoryDto categoryDto);
	public CategoryDto updateCategory(CategoryDto categoryDto , Integer categoryId);
	public void deleteCategory(Integer categoryId);
	public CategoryDto getCategory(Integer categoryId);
	List<CategoryDto> getallCategory();
	
}
