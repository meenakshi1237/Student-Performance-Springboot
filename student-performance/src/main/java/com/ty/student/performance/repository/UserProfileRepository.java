package com.ty.student.performance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.student.performance.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

}
