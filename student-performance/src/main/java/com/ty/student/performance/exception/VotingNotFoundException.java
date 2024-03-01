package com.ty.student.performance.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class VotingNotFoundException extends RuntimeException {

	private String message = "Voting not found";

	@Override
	public String getMessage() {
		return this.message;
	}
	
	
	
}
