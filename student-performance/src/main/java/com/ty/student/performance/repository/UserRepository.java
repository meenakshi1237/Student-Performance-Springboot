package com.ty.student.performance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.student.performance.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
