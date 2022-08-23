package com.BikkadIT.blog.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.BikkadIT.blog.exceptions.ResourceNotFoundException;
import com.BikkadIT.blog.paylods.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourseNOtFoundExceptioHandler(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponse apiresponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<Map<String , String>>
		HandleMethodArgNotValidException(MethodArgumentNotValidException ex){
	   Map<String , String> resp=new HashMap<>();	
	   ex.getBindingResult().getAllErrors().forEach((error)->{
		 String fieldname = ((FieldError)error).getField();
		 String message = error.getDefaultMessage();
		 resp.put(fieldname, message);
	   });

	return new ResponseEntity<Map<String , String>>(resp, HttpStatus.BAD_REQUEST) ;
			
		}

}
