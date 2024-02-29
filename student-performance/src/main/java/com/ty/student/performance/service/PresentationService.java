package com.ty.student.performance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.student.performance.dao.PresentationDao;
import com.ty.student.performance.dto.ResponseStructure;
import com.ty.student.performance.entity.Presentation;
import com.ty.student.performance.entity.User;
import com.ty.student.performance.exception.UserNotAuthorizedException;
import com.ty.student.performance.exception.UserNotFoundException;
import com.ty.student.performance.repository.PresentationRepository;
import com.ty.student.performance.repository.UserRepository;
import com.ty.student.performance.util.UserRole;

@Service
public class PresentationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PresentationRepository presentationRepository;

	@Autowired
	private PresentationDao presentationDao;

	public ResponseEntity<ResponseStructure<Presentation>> savePresentation(Presentation presentation, int studentId) {
		Optional<User> opt = userRepository.findById(studentId);

		if (opt.isPresent()) {
			if (opt.get().getUserRole().equals(UserRole.valueOf("STUDENT"))) {
				presentation.setUser(opt.get());

				Presentation newPresentation = presentationDao.savePresentation(presentation);

				ResponseStructure<Presentation> responseStructure = new ResponseStructure<Presentation>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setData(newPresentation);
				responseStructure.setMessage("Presentation Saved");

				return new ResponseEntity<ResponseStructure<Presentation>>(responseStructure, HttpStatus.CREATED);

			} else {
				throw new UserNotAuthorizedException();
			}
		} else {
			throw new UserNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<Presentation>> addTrainerMark(int presentationId, int trainerId,
			double trainerMark) {
		Optional<Presentation> optPresentation = presentationRepository.findById(presentationId);
		Optional<User> optUser = userRepository.findById(trainerId);

		if (optUser.isPresent()) {
			if (optUser.get().getUserRole().equals(UserRole.valueOf("TRAINER"))) {
				optPresentation.get().setTrainerId(trainerId);
				optPresentation.get().setTrainerMark(trainerMark);

				Presentation updatedPresentation = presentationDao.addTrainerMark(optPresentation.get());

				ResponseStructure<Presentation> responseStructure = new ResponseStructure<Presentation>();
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setData(updatedPresentation);
				responseStructure.setMessage("Trainer mark added");

				return new ResponseEntity<ResponseStructure<Presentation>>(responseStructure, HttpStatus.OK);
			} else {
				throw new UserNotAuthorizedException();
			}
		} else {
			throw new UserNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<List<Presentation>>> getPresentationByStudentId(int studentId) {
		Optional<User> optUser = userRepository.findById(studentId);

		if (optUser.isPresent()) {
			if (optUser.get().getUserRole().equals(UserRole.valueOf("STUDENT"))) {
				List<Presentation> presentationLists = presentationDao.getPresentationBytsudentId(optUser.get());

				ResponseStructure<List<Presentation>> responseStructure = new ResponseStructure<List<Presentation>>();
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setData(presentationLists);
				responseStructure.setMessage("Presentation list fetched");

				return new ResponseEntity<ResponseStructure<List<Presentation>>>(responseStructure, HttpStatus.OK);
			} else {
				throw new UserNotAuthorizedException();
			}
		} else {
			throw new UserNotFoundException();
		}

	}

	public ResponseEntity<ResponseStructure<List<Presentation>>> getAllPresentation(int trainerId) {
		Optional<User> optUser = userRepository.findById(trainerId);

		if (optUser.isPresent()) {
			if (optUser.get().getUserRole().equals(UserRole.valueOf("TRAINER"))) {
				List<Presentation> presentationLists = presentationDao.getAllPresentation();

				ResponseStructure<List<Presentation>> responseStructure = new ResponseStructure<List<Presentation>>();
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setData(presentationLists);
				responseStructure.setMessage("Presentation list fetched");

				return new ResponseEntity<ResponseStructure<List<Presentation>>>(responseStructure, HttpStatus.OK);
			} else {
				throw new UserNotAuthorizedException();
			}
		} else {
			throw new UserNotFoundException();
		}
	}

}
