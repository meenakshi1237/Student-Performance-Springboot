package com.ty.student.performance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.student.performance.entity.User;
import com.ty.student.performance.util.UserRole;

public interface UserRepository extends JpaRepository<User, Integer>{

	List<User> findByUserRole(UserRole role);

}
