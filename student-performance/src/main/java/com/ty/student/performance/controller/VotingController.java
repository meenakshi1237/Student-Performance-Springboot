package com.ty.student.performance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("users/votings/")
@Tag(name = "Voting", description = "voting related")
public class VotingController {

	@Autowired
	private VotingService votingService;
	
	@Operation(description = "To Create Votings", summary = "votings will be created")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "CREATED"),@ApiResponse(responseCode = "404",description = "`NOT FOUND`", content = @Content),@ApiResponse(responseCode = "401",description = "`NOT AUTHORIZED`", content = @Content),@ApiResponse(responseCode = "400",description = "`BAD REQUEST`", content = @Content)})
	@PostMapping(value="students/{studentId}/presentations/{presentationId}",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<Voting>> saveVoting(@PathVariable int studentId, @PathVariable int presentationId, @RequestBody Voting voting) {
		return this.votingService.saveVoting(studentId, presentationId, voting);
	}
	
	@Operation(description = "To Get Voting", summary = "voting will be displayed")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),@ApiResponse(responseCode = "404",description = "`NOT FOUND`", content = @Content)})
	@GetMapping(value="{votingId}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<Voting>> getVotingById(@PathVariable int votingId) {
		return this.votingService.getVotingById(votingId);
	}
	
	@Operation(description = "To Get ALL Voting For Presentation", summary = "votings will be displayed for presentation")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),@ApiResponse(responseCode = "400",description = "`BAD REQUEST`", content = @Content)})
	@GetMapping(value="presentations/{presentationId}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<List<Voting>>> getAllVotingByPresentationId(@PathVariable int presentationId) {
		return this.votingService.getAllVotingByPresentationId(presentationId);
	}
	
}
