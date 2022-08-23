package com.BikkadIT.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BikkadIT.blog.entities.Category;
import com.BikkadIT.blog.entities.Post;
import com.BikkadIT.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
}
