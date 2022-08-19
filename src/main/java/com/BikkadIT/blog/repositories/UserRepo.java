package com.BikkadIT.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BikkadIT.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	
	
	
}
