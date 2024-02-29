package com.ty.student.performance.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ty.student.performance.dao.UserProfileDao;
import com.ty.student.performance.dto.ResponseStructure;
import com.ty.student.performance.entity.User;
import com.ty.student.performance.entity.UserProfile;
import com.ty.student.performance.exception.UserNotFoundException;
import com.ty.student.performance.repository.UserRepository;
import com.ty.student.performance.util.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	@Value("${project.image}")
	private String path;
	
	@Autowired
	private UserProfileDao userProfileDao;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<ResponseStructure<UserProfile>> saveUserProfile(int id,UserProfile profile, MultipartFile file) throws IOException {
		
		//finding the user by id 
		User user=userRepository.findById(id).orElseThrow(()->new UserNotFoundException());
		
		//setting the profile to ths user
		user.setUserProfile(profile);
		
		//File name
				String name = file.getOriginalFilename();
				
				//Fullpath
				String filePath = path+File.separator+name;
				
				//create folder if not created
				File f = new File(path);
				if(!f.exists()) {
					f.mkdir();
				}
				
				//file copy
				Files.copy(file.getInputStream(), Paths.get(filePath));
				
				profile.setPath(filePath);
				
				UserProfile saved_userProfile= userProfileDao.saveUserProfile(profile);
				
					ResponseStructure<UserProfile> response=new ResponseStructure<UserProfile>();
					
					response.setData(saved_userProfile);
					response.setMessage("User profile created for the user");
					response.setStatusCode(HttpStatus.CREATED.value());
					
					return new ResponseEntity<ResponseStructure<UserProfile>>(response,HttpStatus.CREATED);
				
		
	}

	

}
