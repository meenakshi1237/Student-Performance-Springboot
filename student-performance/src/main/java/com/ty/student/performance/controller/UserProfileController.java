package com.ty.student.performance.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ty.student.performance.dto.ResponseStructure;
import com.ty.student.performance.entity.UserProfile;
import com.ty.student.performance.service.UserProfileServiceImpl;

@RestController
@RequestMapping("/users")
public class UserProfileController {

	
	@Autowired
	private UserProfileServiceImpl userProfileService;
	
	
	//to set user profile to the existing user
	@PostMapping("/{id}/userprofile")
	public ResponseEntity<ResponseStructure<UserProfile>> saveUserProfile(@PathVariable int id, UserProfile userProfile,@RequestParam("image") MultipartFile photofile) throws IOException{
		
		return userProfileService.saveUserProfile(id,userProfile,photofile);	
	}
	
	//to delete the profile for the existing user
	
	@DeleteMapping("/{user_id}/userprofile")
	public ResponseEntity<ResponseStructure<UserProfile>> delteUserProfile(@PathVariable int user_id){
		
		return userProfileService.deleteUserProfile(user_id);	
	}
	
	
	
}
