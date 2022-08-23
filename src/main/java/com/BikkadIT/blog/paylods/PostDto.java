package com.BikkadIT.blog.paylods;

import java.util.Date;
import javax.persistence.Column;

import org.hibernate.validator.constraints.Normalized;

import com.BikkadIT.blog.entities.Category;
import com.BikkadIT.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {	
	
	private Integer postId;
	
	private String title;
	
	private String content;
	
	private Date addedDate;
	
	private String ImageName;
	
    private CategoryDto category;
	
	private UserDto user;
}
