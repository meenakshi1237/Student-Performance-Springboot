package com.ty.student.performance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.student.performance.entity.Voting;

public interface VotingRepository extends JpaRepository<Voting, Integer> {

}
