package com.ty.student.performance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.student.performance.dao.VotingDao;
import com.ty.student.performance.dto.ResponseStructure;import com.ty.student.performance.entity.Presentation;
import com.ty.student.performance.entity.User;
import com.ty.student.performance.entity.Voting;
import com.ty.student.performance.exception.UserNotAuthorizedException;
import com.ty.student.performance.exception.UserNotFoundException;
import com.ty.student.performance.exception.VotingListEmptyException;
import com.ty.student.performance.exception.VotingNotFoundException;
import com.ty.student.performance.repository.PresentationRepository;
import com.ty.student.performance.repository.UserRepository;
import com.ty.student.performance.util.UserRole;

@Service
public class VotingService {

	@Autowired
	private VotingDao votingDao;
	
	@Autowired
	private PresentationRepository presentationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<ResponseStructure<Voting>> saveVoting(int userId ,int presentationId, Voting voting) {
		
		Optional<Presentation> optional = this.presentationRepository.findById(presentationId);
		Optional<User> optional2 = this.userRepository.findById(userId);
		
		if(optional.isPresent() && optional2.isPresent()) {
			Presentation presentation = optional.get();
			User user = optional2.get();
			if(user.getUserRole().equals(UserRole.STUDENT) && userId != presentation.getUser().getUserId()) {
				this.votingDao.saveVoting(presentationId, voting);
				ResponseStructure<Voting> responseStructure = new ResponseStructure<Voting>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Success");
				responseStructure.setData(voting);
				
				return new ResponseEntity<ResponseStructure<Voting>>(responseStructure, HttpStatus.CREATED);
			} else {
				throw new UserNotAuthorizedException();
			}
		} else {
			throw new UserNotFoundException();
		}
		
	}
	
	public ResponseEntity<ResponseStructure<Voting>> getVotingById(int votingId) {
		Voting voting = this.votingDao.getVotingById(votingId);
		if(voting != null) {
			ResponseStructure<Voting> responseStructure = new ResponseStructure<Voting>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(voting);
			
			return new ResponseEntity<ResponseStructure<Voting>>(responseStructure, HttpStatus.OK);
		} else {
			throw new VotingNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Voting>>> getAllVotingByPresentationId(int presentationId) {
		List<Voting> votings = this.votingDao.getAllVotingByPresentatiinId(presentationId);
		if(!votings.isEmpty()) {
			ResponseStructure<List<Voting>> responseStructure = new ResponseStructure<List<Voting>>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(votings);
			
			return new ResponseEntity<ResponseStructure<List<Voting>>>(responseStructure, HttpStatus.OK);
		} else {
			throw new VotingListEmptyException();
		}
	}
	
}
