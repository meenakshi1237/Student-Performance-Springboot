package com.ty.student.performance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.student.performance.dao.UserDao;
import com.ty.student.performance.dto.ResponseStructure;
import com.ty.student.performance.entity.User;
import com.ty.student.performance.util.UserRole;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public ResponseEntity<ResponseStructure<User>> saveTrainer(User user){
		user.setUserRole(UserRole.TRAINER);
		User saveduser=userDao.saveUser(user);
		ResponseStructure<User> responseStructure=new ResponseStructure<User>();
		responseStructure.setData(saveduser);
		responseStructure.setMessage("Sucess");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<User>> saveStudent(User user){
		user.setUserRole(UserRole.STUDENT);
		User saveduser=userDao.saveUser(user);
		ResponseStructure<User> responseStructure=new ResponseStructure<User>();
		responseStructure.setData(saveduser);
		responseStructure.setMessage("Sucess");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
	}
}
