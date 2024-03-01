package com.ty.student.performance.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.student.performance.entity.UserProfile;
import com.ty.student.performance.repository.UserProfileRepository;

@Repository
public class UserProfileDao {

	@Autowired
	private UserProfileRepository userProfileRepository;

	public UserProfile saveUserProfile(UserProfile profile) {
		
		return userProfileRepository.save(profile);
	}
	

}
