package com.ty.student.performance.exception;

public class UserNotAuthorized extends RuntimeException{
	@Override
	public String getMessage() {
		return "User not authorized";
	}

}
