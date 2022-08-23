package com.BikkadIT.blog.services.Impl;

import com.BikkadIT.blog.exceptions.*;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BikkadIT.blog.entities.User;
import com.BikkadIT.blog.paylods.UserDto;
import com.BikkadIT.blog.repositories.UserRepo;
import com.BikkadIT.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelmapper;

	@Override                                       //method for create
	public UserDto createUser(UserDto userdto) {
		User user = this.dtoToUser(userdto);
		User saveeduser = this.userRepo.save(user);

		return this.userToDto(saveeduser);
	}

	@Override                                     //method for update
	public UserDto updateUser(UserDto userdto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id ", userId));

		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());
		User updateduser = userRepo.save(user);
		UserDto userDto2 = this.userToDto(updateduser);
		return userDto2;
	}

	@Override                                      //method for getBYId
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return this.userToDto(user);
		
		
		
	}

	@Override                                       //method for getAll	                                                      
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override                                      //method for deletById
	public void deleteUser(Integer userId) {
		
		
		User user=this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user","Id",userId));

		this.userRepo.delete(user);
	}

	public User dtoToUser(UserDto userdto) {
		User user = this.modelmapper.map(userdto, User.class);
		
		
//		user.setId(userdto.getId());
//		user.setName(userdto.getName());
//		user.setEmail(userdto.getEmail());
//		user.setPassword(userdto.getPassword());
//		user.setAbout(userdto.getAbout());
		
	return user;

	}

	public UserDto userToDto(User user) {
		UserDto userdto = this.modelmapper.map(user, UserDto.class);

//		userdto.setId(user.getId());
//		userdto.setName(user.getName());
//		userdto.setEmail(user.getEmail());
//		userdto.setPassword(user.getPassword());
//		userdto.setAbout(user.getAbout());
		
		return userdto;

	}

}
