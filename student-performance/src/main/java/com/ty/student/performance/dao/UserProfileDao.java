package com.ty.student.performance.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.student.performance.repository.UserProfileRepository;

@Repository
public class UserProfileDao {

	@Autowired
	private UserProfileRepository userProfileRepository;
}
