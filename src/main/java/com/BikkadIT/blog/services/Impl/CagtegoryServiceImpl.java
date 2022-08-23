package com.BikkadIT.blog.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BikkadIT.blog.entities.Category;
import com.BikkadIT.blog.exceptions.ResourceNotFoundException;
import com.BikkadIT.blog.paylods.CategoryDto;
import com.BikkadIT.blog.repositories.CategoryRepo;
import com.BikkadIT.blog.services.CategoryService;

@Service
public class CagtegoryServiceImpl  implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryrepo;
	
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category cat = this.modelmapper.map(categoryDto, Category.class);
		Category addcat = this.categoryrepo.save(cat);
		return this.modelmapper.map(addcat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category cat = this.categoryrepo.findById(categoryId)
		.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatecat = this.categoryrepo.save(cat);
		return this.modelmapper.map(updatecat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = this.categoryrepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId)); 
		this.categoryrepo.deleteById(categoryId);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
      Category cat = this.categoryrepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		return this.modelmapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getallCategory() {
		List<Category> categories = this.categoryrepo.findAll();
		List<CategoryDto> catDtos = categories.stream().map((cat)-> this.modelmapper.map(cat, CategoryDto.class))
		.collect(Collectors.toList());
		return catDtos;
	}

}
