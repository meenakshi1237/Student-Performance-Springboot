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
import com.ty.student.performance.exception.PresentationListEmptyException;
import com.ty.student.performance.exception.UserNotAuthorizedException;
import com.ty.student.performance.exception.UserNotFoundException;
import com.ty.student.performance.repository.PresentationRepository;
import com.ty.student.performance.repository.UserRepository;
import com.ty.student.performance.util.UserRole;

@Service
public class PresentationService {

	@Autowired
	private PresentationDao presentationDao;

	public ResponseEntity<ResponseStructure<Presentation>> savePresentation(Presentation presentation, int studentId) {

		User user = presentationDao.findUserById(studentId);

		if (user != null) {
			if (user.getUserRole().equals(UserRole.valueOf("STUDENT"))) {
				presentation.setUser(user);

				presentation.setTrainerId(0);
				presentation.setTrainerMark(0.0);
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

		Presentation presentation = presentationDao.getPresentationById(presentationId);
		User user = presentationDao.findUserById(trainerId);

		if (user != null) {
			if (user.getUserRole().equals(UserRole.valueOf("TRAINER"))) {
				presentation.setTrainerId(trainerId);
				presentation.setTrainerMark(trainerMark);

				Presentation updatedPresentation = presentationDao.addTrainerMark(presentation);

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
		User user = presentationDao.findUserById(studentId);

		if (user != null) {
			if (user.getUserRole().equals(UserRole.valueOf("STUDENT"))) {
				List<Presentation> presentationLists = presentationDao.getPresentationByStudentId(user);

				if (!presentationLists.isEmpty()) {
					ResponseStructure<List<Presentation>> responseStructure = new ResponseStructure<List<Presentation>>();
					responseStructure.setStatusCode(HttpStatus.OK.value());
					responseStructure.setData(presentationLists);
					responseStructure.setMessage("Presentation list fetched");

					return new ResponseEntity<ResponseStructure<List<Presentation>>>(responseStructure, HttpStatus.OK);
				} else {
					throw new PresentationListEmptyException();
				}

			} else {
				throw new UserNotAuthorizedException();
			}
		} else {
			throw new UserNotFoundException();
		}

	}

	public ResponseEntity<ResponseStructure<List<Presentation>>> getAllPresentationByTrainerId(int trainerId) {
		User user = presentationDao.findUserById(trainerId);

		if (user != null) {
			if (user.getUserRole().equals(UserRole.valueOf("TRAINER"))) {
				List<Presentation> presentationLists = presentationDao.getAllPresentationByTrainerId(trainerId);

				if (!presentationLists.isEmpty()) {
					ResponseStructure<List<Presentation>> responseStructure = new ResponseStructure<List<Presentation>>();
					responseStructure.setStatusCode(HttpStatus.OK.value());
					responseStructure.setData(presentationLists);
					responseStructure.setMessage("Presentation list fetched");

					return new ResponseEntity<ResponseStructure<List<Presentation>>>(responseStructure, HttpStatus.OK);
				} else {
					throw new PresentationListEmptyException();
				}

			} else {
				throw new UserNotAuthorizedException();
			}
		} else {
			throw new UserNotFoundException();
		}
	}

}
