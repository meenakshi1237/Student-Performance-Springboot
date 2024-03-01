package com.ty.student.performance.exception;

import java.io.IOException;

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
		
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setMessage(""+exception.getMessage());
		structure.setData("Not found");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotAuthorizedException.class)
	public ResponseEntity<ResponseStructure<String>> handleUserNotAuthorizedException(UserNotAuthorizedException exception){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		
		structure.setStatusCode(HttpStatus.UNAUTHORIZED.value());
		structure.setMessage(""+exception.getMessage());
		structure.setData("Not found");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<ResponseStructure<String>> handleIOException(IOException exception){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage(exception.getMessage());
		structure.setData("Not successful");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(VotingListEmptyException.class)
	public ResponseEntity<ResponseStructure<String>> handleVotingListEmptyException(VotingListEmptyException exception){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		
		structure.setStatusCode(HttpStatus.NO_CONTENT.value());
		structure.setMessage(exception.getMessage());
		structure.setData("No Content");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> handleUserAlreadyPresentException(SQLIntegrityConstraintViolationException exception){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage(""+exception.getMessage());
		structure.setData("Not Found");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserProfileNotFundException.class)
	public ResponseEntity<ResponseStructure<String>> handleUserProfileNotFundException(UserProfileNotFundException exception){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setMessage(exception.getMessage());
		structure.setData(null);
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PhotoNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlePhotoNotFoundException(PhotoNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setMessage(exception.getMessage());
		structure.setData("Deletion Not possible");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler(TrainerDeletionException.class)
	public ResponseEntity<ResponseStructure<String>> handlerTrainerCannotBeDeleted(TrainerDeletionException exception){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage(""+exception.getMessage());
		structure.setData("Not Found");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(VotingAlreadyExistException.class)
	public ResponseEntity<ResponseStructure<String>> handleVotingAlreadyExistException(VotingAlreadyExistException exception){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage(exception.getMessage());
		structure.setData("Voting Already Exist");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(VotingNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleVotingNotFoundException(VotingNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setMessage(exception.getMessage());
		structure.setData("Not Found");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PresentationNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlePresentationNotFoundException(PresentationNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setMessage(exception.getMessage());
		structure.setData("Not Found");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PresentationListEmptyException.class)
	public ResponseEntity<ResponseStructure<String>> handlePresentationListEmptyException(PresentationListEmptyException exception){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		
		structure.setStatusCode(HttpStatus.NO_CONTENT.value());
		structure.setMessage(exception.getMessage());
		structure.setData("No Content");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NO_CONTENT);
	}
}
