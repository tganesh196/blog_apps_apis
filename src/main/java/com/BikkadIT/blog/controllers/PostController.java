package com.BikkadIT.blog.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.blog.entities.Post;
import com.BikkadIT.blog.paylods.ApiResponse;
import com.BikkadIT.blog.paylods.PostDto;
import com.BikkadIT.blog.paylods.PostResponse;
import com.BikkadIT.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postservice;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createpost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto createpost = this.postservice.createpost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createpost, HttpStatus.CREATED);

	}

	// Get post By User
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getpostByUser(@PathVariable Integer userId) {
		List<PostDto> posts = this.postservice.getpostByuser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// Get post By User
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getpostByCategory(@PathVariable Integer categoryId) {
		List<PostDto> posts = this.postservice.getpostBycategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

	}

	//get all
//	@GetMapping("/posts")
//	public ResponseEntity<List<PostDto>> getAllposts() {
//		List<PostDto> getallpost = this.postservice.getallpost();
//		return new ResponseEntity<List<PostDto>>(getallpost, HttpStatus.OK);
//	}
	
	
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllposts(
			@RequestParam(value="pageNumber",defaultValue = "0", required = false)Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue = "5", required = false)Integer pageSize
			) {
		
               PostResponse postResponse = this.postservice.getallpost(pageNumber, pageSize);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}
	
	
	
	
	
	
	

	//get by id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getpostById(@PathVariable Integer postId) {
		PostDto getpost = this.postservice.getpost(postId);
		return new ResponseEntity<PostDto>(getpost, HttpStatus.OK);

	}

	//delete By id
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deleteById(@PathVariable Integer postId) {
		this.postservice.deletepost(postId);
		return new ApiResponse("post deleted successfully", true);

	}
	
	//update postBy id
	

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatepost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatepost = this.postservice.updatepost(postDto, postId);
		return new ResponseEntity<PostDto>(updatepost, HttpStatus.CREATED);
	}
	
}
