package com.ty.student.performance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.student.performance.dao.UserDao;
import com.ty.student.performance.dto.ResponseStructure;
import com.ty.student.performance.entity.User;
import com.ty.student.performance.exception.UserNotAuthorizedException;
import com.ty.student.performance.exception.UserNotFoundException;
import com.ty.student.performance.util.UserRole;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;

	public ResponseEntity<ResponseStructure<List<User>>> findAllUsers(int id,String role) {
		
		User user=userDao.findUser(id);
		
		if(user!=null) {
			
			if(user.getUserRole().equals(UserRole.TRAINER)) {
				
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
	
	
	
	

}

