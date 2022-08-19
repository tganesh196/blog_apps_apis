package com.BikkadIT.blog.controllers;

import java.util.List;
import java.util.Map;

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
import com.BikkadIT.blog.paylods.UserDto;
import com.BikkadIT.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userservice;

	// post create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createuser(@RequestBody UserDto userDto) {
		UserDto createUserDto = this.userservice.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.OK);

	}

	// put update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateuser(@RequestBody UserDto userDto, @PathVariable Integer userId) {
		UserDto updateUser = this.userservice.updateUser(userDto, userId);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);

	}

	// Delete delete user without api response class
	@DeleteMapping("/{userId}")
	
//	public ResponseEntity<UserDto> deleteuser(@RequestBody UserDto userDto , @PathVariable Integer userId){
//		this.userservice.deleteUser(userId);
//		return new ResponseEntity(Map.of("message" , "user deleted successfully") ,HttpStatus.OK);

	public ResponseEntity<ApiResponse> deleteuser( @PathVariable Integer userId) {
		this.userservice.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully", true), HttpStatus.OK);

	}

	// Get getAlluser
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getallusers() {
		return ResponseEntity.ok(this.userservice.getAllUsers());

		// List<UserDto> allUsers = this.userservice.getAllUsers();
		// return new ResponseEntity<>(allUsers,HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getsingleuser(@PathVariable Integer userId) {
		return ResponseEntity.ok(this.userservice.getUserById(userId));

	}

}
