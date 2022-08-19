package com.BikkadIT.blog.services;

import java.util.List;

import com.BikkadIT.blog.paylods.UserDto;

public interface UserService {
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto userdto, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);

}
