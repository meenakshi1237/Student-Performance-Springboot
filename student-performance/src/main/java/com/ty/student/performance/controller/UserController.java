package com.ty.student.performance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	//Api to save User As a Trainer
	@PostMapping("/trainer")
	public ResponseEntity<ResponseStructure<User>> saveTrainer(@RequestBody User user){
		return userService.saveTrainer(user);
	}
	
	//Api to save User As a Student
	
	@PostMapping("/student")
	public ResponseEntity<ResponseStructure<User>> saveStudent(@RequestBody User user){
		return userService.saveStudent(user);
	}
	
	//Api to update User
	@PutMapping("/user/{userId}/{trainerId}")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user,@PathVariable int trainerId,@PathVariable int userId){
		return userService.updateUser(user, userId, trainerId);
	}
	
	//Api to find User by id
	@GetMapping("/user/{userId}")
	public ResponseEntity<ResponseStructure<User>> findUserById(@PathVariable int userId){
		return userService.findUserById(userId);
	}
	
	//Api to delete user
	@DeleteMapping("/student/{studentId}/{trainerId}")
	public ResponseEntity<ResponseStructure<String>> deleteStudent(@PathVariable int studentId,@PathVariable int trainerId){
		return userService.deleteStudent(studentId, trainerId);
	}
}
