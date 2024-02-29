package com.ty.student.performance.controller;

<<<<<<< HEAD
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ty.student.performance.dto.ResponseStructure;
import com.ty.student.performance.entity.User;
import com.ty.student.performance.entity.UserProfile;
import com.ty.student.performance.service.UserProfileServiceImpl;
import com.ty.student.performance.service.UserService;


@Controller
@RequestMapping("/users")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserProfileServiceImpl userProfileService;
	
	
	//to set user profile to the existing user
	@PostMapping("/{id}/userprofile")
	public ResponseEntity<ResponseStructure<UserProfile>> saveUserProfile(@PathVariable int id,@ModelAttribute UserProfile userProfile,@RequestParam("image") MultipartFile photofile) throws IOException{
		
		return userProfileService.saveUserProfile(id,userProfile,photofile);	
	}
	
	//to get the user details if user is a trainer
	@GetMapping("/{id}/{role}")
	public ResponseEntity<ResponseStructure<List<User>>> findAllUsers(@PathVariable int id,@PathVariable String role){
		
		return userService.findAllUsers(id,role);
		
	}
	
	
	
=======
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
>>>>>>> fc0ddb9727a98d8bd3f26d14f3b5b5a45a60a910
}
