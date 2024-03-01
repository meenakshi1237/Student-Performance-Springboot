package com.ty.student.performance.exception;

public class PresentationNotFoundException extends RuntimeException{
	
	@Override
	public String getMessage() {
		return "Presentation not found";
	}

}
