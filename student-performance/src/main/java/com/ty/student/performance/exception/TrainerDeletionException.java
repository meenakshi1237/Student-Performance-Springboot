package com.ty.student.performance.exception;

public class TrainerDeletionException extends RuntimeException{
	public String getMessage() {
		return "Trainer Cannot Be Deleted ";
	}
}
