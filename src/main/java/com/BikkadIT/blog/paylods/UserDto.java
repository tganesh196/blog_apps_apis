package com.BikkadIT.blog.paylods;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min=4,message="minimum 4 character")
	private String name;
	
	@Email
	private String email;
	
	@NotEmpty
	@Size(min=4, max=10, message="password must be min 4 character!!")
	private String password;
	
	@NotEmpty
	private String about;
	

}
