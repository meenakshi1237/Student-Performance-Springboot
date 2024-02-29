package com.ty.student.performance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ty.student.performance.dto.ResponseStructure;
import com.ty.student.performance.entity.User;
import com.ty.student.performance.service.UserService;
import com.ty.student.performance.util.UserRole;

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
	
	//to get the user details if user is a trainer
		@GetMapping("/{id}/{role}")
		public ResponseEntity<ResponseStructure<List<User>>> findAllUsers(@PathVariable int id,@PathVariable UserRole role){
			
			return userService.findAllUsers(id,role);
			
		}

}
