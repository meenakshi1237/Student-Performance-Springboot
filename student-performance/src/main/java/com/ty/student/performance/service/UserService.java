package com.ty.student.performance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.ty.student.performance.dao.UserDao;
import com.ty.student.performance.dto.ResponseStructure;
import com.ty.student.performance.entity.User;

import com.ty.student.performance.exception.TrainerDeletionException;

import com.ty.student.performance.exception.UserNotAuthorizedException;
import com.ty.student.performance.exception.UserNotFoundException;
import com.ty.student.performance.exception.ValidationException;
import com.ty.student.performance.util.UserRole;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	
	
	//Service method to save User As a Trainer
	public ResponseEntity<ResponseStructure<User>> saveTrainer(User user,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			String message="";
			for(FieldError error:bindingResult.getFieldErrors()) {
				message=message+error.getDefaultMessage()+", ";
			}
			throw new ValidationException(message);
		}
		user.setUserRole(UserRole.TRAINER);
		User saveduser = userDao.saveUser(user);
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		responseStructure.setData(saveduser);
		responseStructure.setMessage("Sucess");
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
	}
	
	//Service method to save User As a Student
	public ResponseEntity<ResponseStructure<User>> saveStudent(User user,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			String message="";
			for(FieldError error:bindingResult.getFieldErrors()) {
				message=message+error.getDefaultMessage()+", ";
			}
			throw new ValidationException(message);
		}
		
		user.setUserRole(UserRole.STUDENT);
		User saveduser = userDao.saveUser(user);
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		responseStructure.setData(saveduser);
		responseStructure.setMessage("Sucess");
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<User>>> findAllUsers(int id,UserRole role) {
		
		User user=userDao.findUserById(id);
		
		if(user!=null) {
			
			if(user.getUserRole().equals(UserRole.valueOf("TRAINER"))) {
				
				List<User> users=userDao.findUserByRole(role);
				
				ResponseStructure<List<User>> response=new ResponseStructure<List<User>>();
				response.setData(users);
				response.setMessage("sucess");
				response.setStatusCode(HttpStatus.OK.value());
				
				return new ResponseEntity<ResponseStructure<List<User>>>(response,HttpStatus.OK);
				
				}else {
					throw new UserNotAuthorizedException();
				}
		}else {
			throw new UserNotFoundException();
		}
	}
	
	//find User By id
	public ResponseEntity<ResponseStructure<User>> findUserById(int userId){
		User user=userDao.findUserById(userId);
		if(user!=null) {
			ResponseStructure<User> responseStructure=new ResponseStructure<User>();
			responseStructure.setData(user);
			responseStructure.setMessage("Sucess");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
		}
		else {
			throw new UserNotFoundException();
		}
	}
	
	//Update User By Id
	public ResponseEntity<ResponseStructure<User>> updateUser(User user,int userId,int trainerId){
		User foundUser=userDao.findUserById(userId);
		User trainer=userDao.findUserById(trainerId);
		if(foundUser!=null) {
			if(trainer.getUserRole().equals(UserRole.valueOf("TRAINER"))) {
				if(user.getUserName()!=null) {
					foundUser.setUserName(user.getUserName());
				}
				if(user.getEmail()!=null) {
					foundUser.setEmail(user.getEmail());
				}
				if(user.getPassword()!=null) {
					foundUser.setPassword(user.getPassword());
				}
				
				User updatedUser=userDao.saveUser(foundUser);
				ResponseStructure<User> responseStructure=new ResponseStructure<User>();
				responseStructure.setData(updatedUser);
				responseStructure.setMessage("Sucess");
				responseStructure.setStatusCode(HttpStatus.OK.value());
				
				return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
			}
			else {
				throw new UserNotAuthorizedException();
			}
		}
		else {
			throw new UserNotFoundException();
		}
	}
	
	//Delete user if he is only student
	public ResponseEntity<ResponseStructure<String>> deleteStudent(int studentId,int trainerId){
		User trainer=userDao.findUserById(trainerId);
		User student=userDao.findUserById(studentId);
		if(trainer!=null && student!=null) {
			if(trainer.getUserRole().equals(UserRole.valueOf("TRAINER"))) {
				if(student.getUserRole().equals(UserRole.valueOf("STUDENT"))) {
					boolean result=	userDao.deleteStudent(student);
					if(result=true) {
						ResponseStructure<String> responseStructure=new ResponseStructure<String>();
						responseStructure.setData("Student Deleted");
						responseStructure.setMessage("Sucess");
						responseStructure.setStatusCode(HttpStatus.OK.value());
						
						return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK);
					}
					else {
						ResponseStructure<String> responseStructure=new ResponseStructure<String>();
						responseStructure.setData("Forbidden");
						responseStructure.setMessage("Something Went Wrong");
						responseStructure.setStatusCode(HttpStatus.FORBIDDEN.value());
						
						return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK);
					}
				}
				else {
					throw new TrainerDeletionException();
				}
			}
			else {
				throw new UserNotAuthorizedException();
			}
		}
		else {
			throw new UserNotFoundException();
		}
	}

}
