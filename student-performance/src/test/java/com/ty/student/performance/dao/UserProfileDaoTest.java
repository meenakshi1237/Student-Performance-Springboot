package com.ty.student.performance.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ty.student.performance.entity.User;
import com.ty.student.performance.entity.UserProfile;
import com.ty.student.performance.repository.UserProfileRepository;
import com.ty.student.performance.repository.UserRepository;

@SpringBootTest
class UserProfileDaoTest {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserProfileDao userProfileDao;
	
	@Autowired
	UserProfileRepository userProfileRepository;

	@Test
	public void testSaveUserProfile() {

		User user=userDao.findUserById(2);
		 
        UserProfile userProfile = new UserProfile();
        // Set image path
        userProfile.setPath("/profilePhoto/photo.png"); 

        
        UserProfile savedUserProfile = userProfileDao.saveUserProfile(userProfile);

        
        assertThat(savedUserProfile).isNotNull();
        assertThat(savedUserProfile.getPath()).isEqualTo("/images/profile.jpg"); // Assert image path
		
	}
	@Test
	public void testFindUSerProfile() {
		
		UserProfile userProfile=userProfileRepository.findByUserUserId(2);
		
		assertEquals(userProfile.getProfileId(),userProfileDao.findUserProfile(2).getProfileId());
		
	}

}
