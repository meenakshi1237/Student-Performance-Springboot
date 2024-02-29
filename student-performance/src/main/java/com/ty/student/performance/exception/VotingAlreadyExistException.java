package com.ty.student.performance.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class VotingAlreadyExistException extends RuntimeException {

	private String message = "voting already exists";
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
}
