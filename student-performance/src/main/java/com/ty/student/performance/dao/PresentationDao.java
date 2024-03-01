package com.ty.student.performance.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.student.performance.entity.Presentation;
import com.ty.student.performance.entity.User;
import com.ty.student.performance.repository.PresentationRepository;
import com.ty.student.performance.repository.UserRepository;

@Repository
public class PresentationDao {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PresentationRepository presentationRepository;

	public Presentation savePresentation(Presentation presentation) {

		return presentationRepository.save(presentation);
	}

	public Presentation addTrainerMark(Presentation presentation) {
		return presentationRepository.save(presentation);
	}

	public List<Presentation> getPresentationByStudentId(int studentId) {
		return presentationRepository.findByUserUserId(studentId);
	}

	public List<Presentation> getAllPresentation() {
		return presentationRepository.findAll();
	}

	public Presentation getPresentationById(int presentationId) {
		Optional<Presentation> optional = this.presentationRepository.findById(presentationId);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public User findUserById(int studentId) {
		Optional<User> opt = userRepository.findById(studentId);
		return opt.get();
	}
	
	public List<Presentation> getAllPresentationByTrainerId(int trainerId) {
		return presentationRepository.findByTrainerId(trainerId);
	}

}