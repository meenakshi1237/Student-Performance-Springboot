package com.ty.student.performance.exception;

public class PhotoNotFoundException extends RuntimeException {

	private String message;

	public PhotoNotFoundException() {
		
	}

	public PhotoNotFoundException(String message) {
		this.message=message;
	}

	@Override
	public String getMessage() {
		return "Image Not found";
	}

}
