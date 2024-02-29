package com.ty.student.performance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ty.student.performance.dto.ResponseStructure;
import com.ty.student.performance.entity.User;
import com.ty.student.performance.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
		
	@Autowired
	private UserService userService;
	
	@PostMapping("/trainer")
	public ResponseEntity<ResponseStructure<User>> saveTrainer(@RequestBody User user){
		return userService.saveTrainer(user);
	}
	
	@PostMapping("/student")
	public ResponseEntity<ResponseStructure<User>> saveStudent(@RequestBody User user){
		return userService.saveStudent(user);
	}
}
