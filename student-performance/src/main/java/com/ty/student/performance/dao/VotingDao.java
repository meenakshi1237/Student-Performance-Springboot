package com.ty.student.performance.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.student.performance.entity.Presentation;
import com.ty.student.performance.entity.Voting;
import com.ty.student.performance.repository.PresentationRepository;
import com.ty.student.performance.repository.VotingRepository;

@Repository
public class VotingDao {

	@Autowired
	private VotingRepository votingRepository;
	
	@Autowired
	private PresentationRepository presentationRepository;
	
	public Voting saveVoting(int presentationId, Voting voting) {
		Optional<Presentation> optional = this.presentationRepository.findById(presentationId);
		Presentation presentation = optional.get();
		voting.setPresentation(presentation);
		
		return this.votingRepository.save(voting);
		
	}
	
	public Voting getVotingById(int votingId) {
		Optional<Voting> optional = this.votingRepository.findById(votingId);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public List<Voting> getAllVotingByPresentatiinId(int presentationId) {
		return this.votingRepository.findByPresentationPresentationId(presentationId);
	}
	
}
