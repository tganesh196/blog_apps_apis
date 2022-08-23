package com.BikkadIT.blog.paylods;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
	
	private List<PostDto> content;
	int pageNumber;
	int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean lastPage;
	

}
