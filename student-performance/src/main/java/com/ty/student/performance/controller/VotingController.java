package com.ty.student.performance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.student.performance.dto.ResponseStructure;
import com.ty.student.performance.entity.Voting;
import com.ty.student.performance.service.VotingService;

@RestController
@RequestMapping("users/votings/")
public class VotingController {

	@Autowired
	private VotingService votingService;
	
	@PostMapping("students/{studentId}/presentations/{presentationId}")
	public ResponseEntity<ResponseStructure<Voting>> saveVoting(@PathVariable int studentId, @PathVariable int presentationId, @RequestBody Voting voting) {
		return this.votingService.saveVoting(studentId, presentationId, voting);
	}
	
	@GetMapping("{votingId}")
	public ResponseEntity<ResponseStructure<Voting>> getVotingById(@PathVariable int votingId) {
		return this.votingService.getVotingById(votingId);
	}
	
	@GetMapping("presentations/{presentationId}")
	public ResponseEntity<ResponseStructure<List<Voting>>> getAllVotingByPresentationId(@PathVariable int presentationId) {
		return this.votingService.getAllVotingByPresentationId(presentationId);
	}
	
}
