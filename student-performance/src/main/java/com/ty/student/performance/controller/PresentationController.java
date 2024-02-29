package com.ty.student.performance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RequestMapping("/users/presentation")
@RestController
public class PresentationController {
	
	@Autowired
	private PresentationService presentationService;
	
	@PostMapping("/{studentId}")
	public ResponseEntity<ResponseStructure<Presentation>> savePresentation(@RequestBody Presentation presentation, @PathVariable int studentId) {
		return presentationService.savePresentation(presentation, studentId); 
	}
	
	@PutMapping("/{presentationId}/{trainerId}/{trainerMark}")
	public ResponseEntity<ResponseStructure<Presentation>> addTrainerMark(@PathVariable int presentationId, @PathVariable int trainerId , @PathVariable double trainerMark) {
		return presentationService.addTrainerMark(presentationId, trainerId, trainerMark);
	}
	
	@GetMapping("/{studentId}")
	public ResponseEntity<ResponseStructure<List<Presentation>>> getPresentationByStudentId(@PathVariable int studentId) {
		return presentationService.getPresentationByStudentId(studentId);
	}
	
	@GetMapping("/{trainerId}")
	public ResponseEntity<ResponseStructure<List<Presentation>>> getAllPresentation(@PathVariable int trainerId) {
		return presentationService.getAllPresentation(trainerId);
	}
}
