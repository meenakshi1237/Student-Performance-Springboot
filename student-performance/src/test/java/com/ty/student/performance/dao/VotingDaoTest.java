package com.ty.student.performance.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ty.student.performance.StudentPerformanceApplication;
import com.ty.student.performance.entity.Presentation;
import com.ty.student.performance.entity.User;
import com.ty.student.performance.entity.Voting;

@SpringBootTest(classes = StudentPerformanceApplication.class)
class VotingDaoTest {

	@Autowired
	VotingDao votingDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	PresentationDao presentationDao;
	
	@Disabled
	@Test
	void saveVotingTest() {
		
		Presentation presentation = this.presentationDao.getPresentationById(201);
		User user = this.userDao.findUserById(4);
		
		Voting voting = new Voting();
		voting.setReview("good");
		voting.setSuggestion("improve");
		voting.setPresentation(presentation);
		voting.setUser(user);
		
		assertEquals(voting, this.votingDao.saveVoting(voting));
		
	}
	
	@Test
	void getVotingByExistingId() {
		
		int existingId = 1;
		Voting voting = this.votingDao.getVotingById(existingId);
		assertNotNull(voting);
		
	}
	
	@Test
	void getVotingByNonExistingId() {
		
		int nonExistingId = 10;
		Voting voting = this.votingDao.getVotingById(nonExistingId);
		assertNull(voting);
	}
	
	@Test
	void getListOfVotingsByPresentation() {
		
		List<Voting> votings = this.votingDao.getAllVotingByPresentationId(201);
		
		assertNotNull(votings);
	}

}
