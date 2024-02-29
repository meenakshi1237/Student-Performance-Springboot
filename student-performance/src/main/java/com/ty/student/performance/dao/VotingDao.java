package com.ty.student.performance.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.student.performance.entity.Voting;
import com.ty.student.performance.repository.VotingRepository;

@Repository
public class VotingDao {

	@Autowired
	private VotingRepository votingRepository;
	
	// saving voting
	public Voting saveVoting(Voting voting) {
		return this.votingRepository.save(voting);

	}
	
	// finding voting by voting id
	public Voting getVotingById(int votingId) {
		Optional<Voting> optional = this.votingRepository.findById(votingId);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	// finding all voting of a particular presentation by presentation id
	public List<Voting> getAllVotingByPresentationId(int presentationId) {
		return this.votingRepository.findByPresentationPresentationId(presentationId);
	}
	
}
