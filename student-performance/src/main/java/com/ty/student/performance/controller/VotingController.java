package com.ty.student.performance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ty.student.performance.service.VotingService;

@RestController
public class VotingController {

	@Autowired
	private VotingService votingService;
	
}
