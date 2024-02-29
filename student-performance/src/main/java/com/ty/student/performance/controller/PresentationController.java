package com.ty.student.performance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.student.performance.dto.ResponseStructure;
import com.ty.student.performance.entity.Presentation;
import com.ty.student.performance.service.PresentationService;

@RequestMapping()
@RestController
public class PresentationController {
	
	@Autowired
	private PresentationService presentationService;
	
	@PostMapping("/")
	public ResponseEntity<ResponseStructure<Presentation>> savePresentation(@RequestBody Presentation presentation, @PathVariable int studentId) {
		return presentationService.savePresentation(presentation, studentId); 
	}
}
