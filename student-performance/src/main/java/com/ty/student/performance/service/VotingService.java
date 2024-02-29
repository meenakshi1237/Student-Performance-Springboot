package com.ty.student.performance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.student.performance.dao.VotingDao;

@Service
public class VotingService {

	@Autowired
	private VotingDao votingDao;
	
}
