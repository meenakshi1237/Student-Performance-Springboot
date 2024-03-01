package com.ty.student.performance.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

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
import com.ty.student.performance.exception.PhotoNotFoundException;
import com.ty.student.performance.exception.UserNotFoundException;
import com.ty.student.performance.exception.UserProfileNotFundException;
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
	public ResponseEntity<ResponseStructure<UserProfile>> saveUserProfile(int id, UserProfile profile,
			MultipartFile file) throws IOException {

		// finding the user by id
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());

		// setting the profile to ths user
		profile.setUser(user);

		// File name
		String name = file.getOriginalFilename();

		// generate random Id for each photo

		String randomId = UUID.randomUUID().toString();
		String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));

		// Fullpath
		String filePath = path + File.separator + fileName1;

		// create folder if not created
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		// file copy
		Files.copy(file.getInputStream(), Paths.get(filePath));

		profile.setPath(filePath);

		UserProfile saved_userProfile = userProfileDao.saveUserProfile(profile);

		ResponseStructure<UserProfile> response = new ResponseStructure<UserProfile>();

		response.setData(saved_userProfile);
		response.setMessage("User profile created for the user");
		response.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<UserProfile>>(response, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<UserProfile>> deleteUserProfile(int user_id) {

		// finding the user by id
		User user = userRepository.findById(user_id).orElseThrow(() -> new UserNotFoundException());

		// get the path of image for the existing userprofile

		UserProfile valid_userProfile = user.getUserProfile();

		if (valid_userProfile != null) {

			String filePath = valid_userProfile.getPath();

			if (filePath != null) {

				try {
					// to delete the image in images file
					Files.deleteIfExists(Paths.get(filePath));

					// setting the path attribute to null in database
					valid_userProfile.setPath(null);

					// updating the userProfile in the database
					userProfileDao.saveUserProfile(valid_userProfile);

					ResponseStructure<UserProfile> response = new ResponseStructure<UserProfile>();
					response.setData(valid_userProfile);
					response.setMessage("Image Deleted Sucessfully");
					response.setStatusCode(HttpStatus.OK.value());

					return new ResponseEntity<ResponseStructure<UserProfile>>(response, HttpStatus.OK);

				} catch (IOException e) {

					e.printStackTrace();

					ResponseStructure<UserProfile> response = new ResponseStructure<UserProfile>();
					response.setData(null);
					response.setMessage("Error in Deleting image");
					response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

					return new ResponseEntity<ResponseStructure<UserProfile>>(response,
							HttpStatus.INTERNAL_SERVER_ERROR);

				}
			} else {
				throw new PhotoNotFoundException();
			}

		} else {
			throw new UserProfileNotFundException("User Profile Does not exist for" + user.getUserName());
		}

	}

	public ResponseEntity<ResponseStructure<UserProfile>> updateUserProfile(int id, UserProfile userProfile,
			MultipartFile photofile) throws IOException {

		// Finding the user by id
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());

		// Check if the user already has a profile
		UserProfile existingProfile = user.getUserProfile();
		if (existingProfile == null) {
			// If the user doesn't have a profile, create a new one
			return saveUserProfile(id, userProfile, photofile);
		}

		// Update other profile attributes as needed

		// Handle image update
		if (photofile != null && !photofile.isEmpty()) {
			// File name
			String name = photofile.getOriginalFilename();
			// generate random Id for each photo
			String randomId = UUID.randomUUID().toString();
			String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));
			// Fullpath
			String filePath = path + File.separator + fileName1;
			// file copy
			Files.copy(photofile.getInputStream(), Paths.get(filePath));
			existingProfile.setPath(filePath);

		} else {

			ResponseStructure<UserProfile> response = new ResponseStructure<>();
			response.setData(null);
			response.setMessage("Image Not Updated Successfully");
			response.setStatusCode(HttpStatus.FORBIDDEN.value());

			return new ResponseEntity<ResponseStructure<UserProfile>>(response, HttpStatus.FORBIDDEN);

		}

		UserProfile updatedUserProfile = userProfileDao.saveUserProfile(existingProfile);

		ResponseStructure<UserProfile> response = new ResponseStructure<>();
		response.setData(updatedUserProfile);
		response.setMessage("User profile updated successfully");
		response.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<UserProfile>>(response, HttpStatus.OK);

	}
}
