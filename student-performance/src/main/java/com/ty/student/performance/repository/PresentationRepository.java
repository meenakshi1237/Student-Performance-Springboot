package com.ty.student.performance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ty.student.performance.entity.Presentation;

public interface PresentationRepository extends JpaRepository<Presentation, Integer>{
	
	List<Presentation> findByUserUserId(int studentId);
	
	List<Presentation> findByTrainerId(int trainerId);
}
