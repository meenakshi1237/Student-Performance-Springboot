package com.ty.student.performance.exception;

public class PresentationListEmptyException extends RuntimeException{
	
	@Override
	public String getMessage() {
		return "Presentation list empty";
	}

}
