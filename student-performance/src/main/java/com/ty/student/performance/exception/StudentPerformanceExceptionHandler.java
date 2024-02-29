package com.ty.student.performance.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ty.student.performance.dto.ResponseStructure;


@ControllerAdvice
public class StudentPerformanceExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleUserNotFoundException(UserNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage(""+exception.getMessage());
		structure.setData("Not found");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotAuthorizedException.class)
	public ResponseEntity<ResponseStructure<String>> handleUserNotAuthorizedException(UserNotAuthorizedException exception){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage(""+exception.getMessage());
		structure.setData("Not found");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.BAD_REQUEST);
	}
	
<<<<<<< HEAD
	@ExceptionHandler(VotingListEmptyException.class)
	public ResponseEntity<ResponseStructure<String>> handleVotingListEmptyException(VotingListEmptyException exception){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		
		structure.setStatusCode(HttpStatus.NO_CONTENT.value());
		structure.setMessage(exception.getMessage());
		structure.setData("No Content");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NO_CONTENT);
=======
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> handleUserAlreadyPresentException(SQLIntegrityConstraintViolationException exception){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage(""+exception.getMessage());
		structure.setData("User Already Exist");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.BAD_REQUEST);
>>>>>>> fc0ddb9727a98d8bd3f26d14f3b5b5a45a60a910
	}
}
