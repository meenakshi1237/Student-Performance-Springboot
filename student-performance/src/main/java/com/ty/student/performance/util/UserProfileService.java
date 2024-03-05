package com.ty.student.performance.util;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.ty.student.performance.dto.ResponseStructure;
import com.ty.student.performance.entity.UserProfile;



public interface UserProfileService {
	
	
	
	ResponseEntity<ResponseStructure<UserProfile>> saveUserProfile(int id,UserProfile profile, MultipartFile file) throws IOException;

}
