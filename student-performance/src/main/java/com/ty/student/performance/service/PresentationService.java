package com.ty.student.performance.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.ty.student.performance.dto.ResponseStructure;
import com.ty.student.performance.entity.Presentation;
import com.ty.student.performance.entity.User;
import com.ty.student.performance.exception.UserNotFoundException;
import com.ty.student.performance.repository.UserRepository;
import com.ty.student.performance.util.UserRole;

public class PresentationService {

	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<ResponseStructure<Presentation>> savePresentation(Presentation presentation, int studentId) {
		Optional<User> opt=userRepository.findById(studentId);
		if(opt.isPresent()) {
			if(opt.get().getUserRole().equals(UserRole.valueOf("STUDENT"))){
				presentation.setUser(opt.get());
			}else {
				throw new UserNotFoundException();
			}
			}
	}

}
