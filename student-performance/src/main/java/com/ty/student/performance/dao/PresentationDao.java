package com.ty.student.performance.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.student.performance.entity.Presentation;
import com.ty.student.performance.entity.User;
import com.ty.student.performance.repository.PresentationRepository;
@Repository
public class PresentationDao {
	
	@Autowired
	private PresentationRepository presentationRepository;

	public Presentation savePresentation(Presentation presentation) {
		return presentationRepository.save(presentation);
	}

	public Presentation addTrainerMark(Presentation presentation) {
		return presentationRepository.save(presentation);
	}

	public List<Presentation> getPresentationBytsudentId(User user) {
		return user.getPresentations();
	}

	public List<Presentation> getAllPresentation() {
		return presentationRepository.findAll();
	}

}
