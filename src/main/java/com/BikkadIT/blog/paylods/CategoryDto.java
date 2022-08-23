package com.BikkadIT.blog.paylods;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {

	private Integer categoryId;
	
	@NotBlank
	@Size(min=4 , message = "enter minimum 4 characters")
	private String categoryTitle;
	
	@NotBlank
	@Size(min=7 , message= "enter minumum 7 characters")
	private String categoryDescription;
	
	
}
