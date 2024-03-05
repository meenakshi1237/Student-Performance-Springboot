package com.ty.student.performance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.student.performance.dto.ResponseStructure;
import com.ty.student.performance.entity.Presentation;
import com.ty.student.performance.service.PresentationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/users/presentation")
@RestController
@Tag(name = "Presentation", description = "presentation related")
public class PresentationController {
	
	@Autowired
	private PresentationService presentationService;
	
	@Operation(description = "To Create User Presentation", summary = "user presentation will be created")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Trainer Created"),@ApiResponse(responseCode = "404",description = "`NOT FOUND`", content = @Content),@ApiResponse(responseCode = "401",description = "`NOT AUTHORIZED`", content = @Content)})
	@PostMapping(value="/{studentId}",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<Presentation>> savePresentation(@RequestBody Presentation presentation, @PathVariable int studentId) {
		return presentationService.savePresentation(presentation, studentId); 
	}
	
	@Operation(description = "To Add Trainer Marks", summary = "trainer marks will be added")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),@ApiResponse(responseCode = "404",description = "`NOT FOUND`", content = @Content),@ApiResponse(responseCode = "401",description = "`NOT AUTHORIZED`", content = @Content)})
	@PutMapping(value="/{presentationId}/{trainerId}/{trainerMark}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<Presentation>> addTrainerMark(@PathVariable int presentationId, @PathVariable int trainerId , @PathVariable double trainerMark) {
		return presentationService.addTrainerMark(presentationId, trainerId, trainerMark);
	}
	
	@Operation(description = "To Get All Presentations of Student", summary = "all presentations will be displayed")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),@ApiResponse(responseCode = "404",description = "`NOT FOUND`", content = @Content),@ApiResponse(responseCode = "401",description = "`NOT AUTHORIZED`", content = @Content)})
	@GetMapping(value="/student/{studentId}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<List<Presentation>>> getPresentationByStudentId(@PathVariable int studentId) {
		return presentationService.getPresentationByStudentId(studentId);
	}
	
	@Operation(description = "To Get All Presentations of trainer", summary = "all presentations will be displayed")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),@ApiResponse(responseCode = "404",description = "`NOT FOUND`", content = @Content),@ApiResponse(responseCode = "401",description = "`NOT AUTHORIZED`", content = @Content)})
	@GetMapping(value="/trainer/{trainerId}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<List<Presentation>>> getAllPresentationByTrainerId(@PathVariable int trainerId) {
		return presentationService.getAllPresentationByTrainerId(trainerId);
	}
}
