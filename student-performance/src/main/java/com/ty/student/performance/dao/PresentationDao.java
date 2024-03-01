package com.ty.student.performance.dao;

import java.util.List;
import java.util.Optional;

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

	public List<Presentation> getPresentationByStudentId(User user) {
		return user.getPresentations();
	}

	public List<Presentation> getAllPresentation() {
		return presentationRepository.findAll();
	}
	
	public Presentation getPresetationById(int presentationId) {
		Optional<Presentation> optional = this.presentationRepository.findById(presentationId);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

}