package com.ty.student.performance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.student.performance.entity.Presentation;

public interface PresentationRepository extends JpaRepository<Presentation, Integer>{

}
