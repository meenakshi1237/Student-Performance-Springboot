package com.ty.student.performance.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class VotingListEmptyException extends RuntimeException {

	private String message = "Voting list is empty";
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
}
