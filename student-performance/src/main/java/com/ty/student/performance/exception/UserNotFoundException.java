package com.ty.student.performance.exception;

public class UserNotFoundException extends RuntimeException{
	
	@Override
	public String getMessage() {
		return "User ID not found";
	}
}
