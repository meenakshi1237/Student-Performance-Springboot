package com.ty.student.performance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.student.performance.dao.PresentationDao;
import com.ty.student.performance.dao.VotingDao;
import com.ty.student.performance.dto.ResponseStructure;
import com.ty.student.performance.entity.Presentation;
import com.ty.student.performance.entity.User;
import com.ty.student.performance.entity.Voting;
import com.ty.student.performance.exception.UserNotAuthorizedException;
import com.ty.student.performance.exception.UserNotFoundException;
import com.ty.student.performance.exception.VotingAlreadyExistException;
import com.ty.student.performance.exception.VotingListEmptyException;
import com.ty.student.performance.exception.VotingNotFoundException;
import com.ty.student.performance.repository.UserRepository;
import com.ty.student.performance.util.UserRole;

@Service
public class VotingService {

	@Autowired
	private VotingDao votingDao;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PresentationDao presentationDao;
	
	public ResponseEntity<ResponseStructure<Voting>> saveVoting(int userId ,int presentationId, Voting voting) {
		
		Presentation presentation = this.presentationDao.getPresetationById(presentationId);
		Optional<User> optional = this.userRepository.findById(userId);
		
		if(presentation != null && optional.isPresent()) {
			User user = optional.get();
			if(user.getUserRole().equals(UserRole.STUDENT) && userId != presentation.getUser().getUserId()) {
				List<Voting> votings = this.votingDao.getAllVotingByPresentationId(presentationId);
				boolean isPresent = false;
				for (Voting voting2 : votings) {
					if(userId == voting2.getUser().getUserId()) {
						isPresent = true;
						break;
					}
				}
				if(!isPresent) {
					voting.setUser(user);
					voting.setPresentation(presentation);
					this.votingDao.saveVoting(voting);
					ResponseStructure<Voting> responseStructure = new ResponseStructure<Voting>();
					responseStructure.setStatusCode(HttpStatus.CREATED.value());
					responseStructure.setMessage("Success");
					responseStructure.setData(voting);
					
					return new ResponseEntity<ResponseStructure<Voting>>(responseStructure, HttpStatus.CREATED);
				} else {
					throw new VotingAlreadyExistException();
				}
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
		List<Voting> votings = this.votingDao.getAllVotingByPresentationId(presentationId);
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
