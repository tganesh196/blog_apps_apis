package com.BikkadIT.blog.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.BikkadIT.blog.entities.Category;
import com.BikkadIT.blog.entities.Post;
import com.BikkadIT.blog.entities.User;
import com.BikkadIT.blog.exceptions.ResourceNotFoundException;
import com.BikkadIT.blog.paylods.PostDto;
import com.BikkadIT.blog.paylods.PostResponse;
import com.BikkadIT.blog.repositories.CategoryRepo;
import com.BikkadIT.blog.repositories.PostRepo;
import com.BikkadIT.blog.repositories.UserRepo;
import com.BikkadIT.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postrepo;

	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createpost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "userId", categoryId));

		Post post = this.modelmapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newpost = this.postrepo.save(post);
		return this.modelmapper.map(newpost, PostDto.class);
	}

	@Override
	public PostDto updatepost(PostDto postDto, Integer postId) {

		Post post = this.postrepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));

		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());	
		Post updatedpost = this.postrepo.save(post);
		return this.modelmapper.map(updatedpost, PostDto.class);
	}

	@Override
	public void deletepost(Integer postId) {

		Post post = this.postrepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));

		this.postrepo.delete(post);
	}

//	@Override
//	public List<PostDto> getallpost() {
//		List<Post> allposts = this.postrepo.findAll();
//		List<PostDto> postDtos = allposts.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
//				.collect(Collectors.toList());
//		return postDtos;
//	}
	
	
	
	@Override
	public PostResponse getallpost(Integer pageNumber , Integer pageSize) {
		
		
		Pageable p=PageRequest.of(pageNumber , pageSize);
		 Page<Post> pagepost = this.postrepo.findAll(p);
		 
		 List<Post> allposts = pagepost.getContent();
		 
		 
		List<PostDto> postDtos = allposts.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagepost.getNumber());
		postResponse.setPageSize(pagepost.getSize());
		postResponse.setTotalElements(pagepost.getTotalElements());
		postResponse.setTotalPages(pagepost.getTotalPages());
		postResponse.setLastPage(pagepost.isLast());
		
		return postResponse;
	}
	


	@Override
	public PostDto getpost(Integer postId) {
		Post post = this.postrepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		return this.modelmapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getpostBycategory(Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));
		List<Post> posts = this.postrepo.findByCategory(cat);

		List<PostDto> postDtos = posts.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> getpostByuser(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));

		List<Post> posts = this.postrepo.findByUser(user);

		List<PostDto> postDtos = posts.stream().map((Post) -> this.modelmapper.map(posts, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

}
