package com.BikkadIT.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

	String resourseNsme;
	String fieldName;
	long fieldValue;
	public ResourceNotFoundException(String resourseNsme, String fieldName, long fieldValue) {
		super(String .format("%s npt found with %s : %1",   resourseNsme, fieldName, fieldValue));
		this.resourseNsme = resourseNsme;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
}
