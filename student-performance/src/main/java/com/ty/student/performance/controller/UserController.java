package com.ty.student.performance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.student.performance.dto.ResponseStructure;
import com.ty.student.performance.entity.User;
import com.ty.student.performance.exception.ValidationException;
import com.ty.student.performance.service.UserService;
import com.ty.student.performance.util.UserRole;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "user related")
public class UserController {
		
	@Autowired
	private UserService userService;
	
	//Api to save User As a Trainer
	@Operation(description = "To Create Trainer", summary = "trainer will created")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Trainer Created")})
	@PostMapping(value="/trainer",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<User>> saveTrainer(@Valid @RequestBody User user,BindingResult bindingResult){
		
		return userService.saveTrainer(user,bindingResult);
	}
	
	//Api to save User As a Student
	@Operation(description = "To Create Student", summary = "student will be created")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Student Created")})
	@PostMapping(value = "/student",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<User>> saveStudent(@Valid @RequestBody User user,BindingResult result,BindingResult bindingResult){
		
		return userService.saveStudent(user,bindingResult);
	}
	
	//to get the user details if user is a trainer
	@Operation(description = "To get all users",summary = "users will be display")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),@ApiResponse(responseCode = "404",description = "`NOT FOUND`",content = @Content),@ApiResponse(responseCode = "401",description = "`NOT AUTHORIZED`",content = @Content)})
	@GetMapping(value = "/{id}/{role}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<List<User>>> findAllUsers(@PathVariable int id,@PathVariable UserRole role){
		
		return userService.findAllUsers(id,role);
		
	}
		
	//Api to update User
	@Operation(description = "To update user",summary = "users will be updated")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),@ApiResponse(responseCode = "404",description = "`NOT FOUND`",content = @Content),@ApiResponse(responseCode = "401",description = "`NOT AUTHORIZED`",content = @Content)})
	@PutMapping(value = "/user/{userId}/{trainerId}",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user,@PathVariable int trainerId,@PathVariable int userId){
		return userService.updateUser(user, userId, trainerId);
	}
	
	//Api to find User by id
	@Operation(description = "To get all users",summary = "users will be display")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),@ApiResponse(responseCode = "404",description = "`NOT FOUND`",content = @Content),@ApiResponse(responseCode = "401",description = "`NOT AUTHORIZED`",content = @Content)})
	@GetMapping(value = "/user/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<User>> findUserById(@PathVariable int userId){
		return userService.findUserById(userId);
	}
	
	//Api to delete user
	@Operation(description = "To delete students",summary = "students will be deleted")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),@ApiResponse(responseCode = "404",description = "`NOT FOUND`",content = @Content),@ApiResponse(responseCode = "401",description = "`NOT AUTHORIZED`",content = @Content)})
	@DeleteMapping(value = "/student/{studentId}/{trainerId}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<String>> deleteStudent(@PathVariable int studentId,@PathVariable int trainerId){
		return userService.deleteStudent(studentId, trainerId);
	}

}
