package com.ty.student.performance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.student.performance.entity.Voting;

public interface VotingRepository extends JpaRepository<Voting, Integer> {

	List<Voting> findByPresentationPresentationId(int presentationId);
	
}
