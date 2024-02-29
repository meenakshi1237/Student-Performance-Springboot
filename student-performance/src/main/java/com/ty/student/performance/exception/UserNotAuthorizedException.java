package com.ty.student.performance.exception;

public class UserNotAuthorizedException extends RuntimeException{
	@Override
	public String getMessage() {
		return "User not authorized";
	}

}
