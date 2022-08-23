package com.BikkadIT.blog.services;

import java.util.List;

import com.BikkadIT.blog.entities.Post;
import com.BikkadIT.blog.paylods.PostDto;
import com.BikkadIT.blog.paylods.PostResponse;

public interface PostService {

	//create post
	PostDto createpost(PostDto postDto, Integer userId, Integer categoryId);

	//update post by id
	PostDto updatepost(PostDto postDto, Integer postId);
	
	// deleter post by id
	void deletepost( Integer postId);
	
	//getallpost
	
//	List<PostDto> getallpost();
	
    PostResponse getallpost(Integer pageNumber , Integer pageSize);
	
	//get post by id
	PostDto getpost(Integer postId);
	
	
	// get post by caegory id
	List<PostDto> getpostBycategory(Integer categoryId);
	
	
	//get allposts by user
	List<PostDto> getpostByuser(Integer userId);
	
	
	
}
