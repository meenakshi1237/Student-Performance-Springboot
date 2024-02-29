package com.ty.student.performance.exception;

public class UserProfileNotFundException extends RuntimeException{
	
	private String message;
	
	public UserProfileNotFundException() {
		
	}
	
	public UserProfileNotFundException(String message) {
		this.message=message;
	}
	
	
	@Override
	public String getMessage() {
		return "User Profile not found gor existing user";
	}

}
