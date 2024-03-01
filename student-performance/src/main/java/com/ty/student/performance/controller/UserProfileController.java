package com.ty.student.performance.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ty.student.performance.dto.ResponseStructure;
import com.ty.student.performance.entity.UserProfile;
import com.ty.student.performance.service.UserProfileServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "User Profile", description = "user profile related")
public class UserProfileController {

	@Autowired
	private UserProfileServiceImpl userProfileService;

	// to set user profile to the existing user
	@Operation(description = "To Create User Profile", summary = "user profile will be created")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Trainer Created"),@ApiResponse(responseCode = "404",description = "`NOT FOUND`", content = @Content)})
	@PostMapping(value = "/{id}/userprofile",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<UserProfile>> saveUserProfile(@PathVariable int id, UserProfile userProfile,
			@RequestParam("image") MultipartFile photofile) throws IOException {

		return userProfileService.saveUserProfile(id, userProfile, photofile);
	}

	// to delete the profile for the existing user

	@Operation(description = "To Delete User Profile Image", summary = "user profile image will be deleted")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),@ApiResponse(responseCode = "500",description = "`INTERNAL SERVER ERROR`", content = @Content),@ApiResponse(responseCode = "404",description = "`NOT FOUND`", content = @Content)})
	@DeleteMapping(value = "/{user_id}/userprofile", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<UserProfile>> delteUserProfile(@PathVariable int user_id) {

		return userProfileService.deleteUserProfile(user_id);
	}

	// to update the profile for existing user
	@Operation(description = "To Update User Profile", summary = "user profile will be updated")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),@ApiResponse(responseCode = "403",description = "`FORBIDDEN`", content = @Content),@ApiResponse(responseCode = "404",description = "`NOT FOUND`", content = @Content)})
	@PutMapping(value = "/{id}/userprofile",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<UserProfile>> updateUserProfile(@PathVariable int id,
			UserProfile userProfile, @RequestParam("image") MultipartFile photofile) throws IOException {

		return userProfileService.updateUserProfile(id, userProfile, photofile);
	}
	
	//to find the user profile user id
	@Operation(description = "To Get User Profile", summary = "user profile will be displayed")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK") ,@ApiResponse(responseCode = "404",description = "`NOT FOUND`", content = @Content)})
	@GetMapping(value = "/{id}/userprofile", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<UserProfile>> findUserProfile(@PathVariable int id) {

		return userProfileService.findUserProfile(id);
	}


}
