package com.ty.student.performance.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.student.performance.entity.User;
import com.ty.student.performance.repository.UserRepository;

@Repository
public class UserDao {
	
	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User findUserById(int userId) {
		Optional<User> optional= userRepository.findById(userId);
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			return null;
		}
	}
	
	public boolean deleteStudent(User user) {
		if(user!=null) {
			userRepository.delete(user);
			return true;
		}
		else {
			return false;
		}
	}
	
	
}
