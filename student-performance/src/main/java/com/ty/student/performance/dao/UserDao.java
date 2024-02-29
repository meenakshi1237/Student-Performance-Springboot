package com.ty.student.performance.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.student.performance.entity.User;
import com.ty.student.performance.repository.UserRepository;
import com.ty.student.performance.util.UserRole;

@Repository
public class UserDao {
	
	@Autowired
	private UserRepository userRepository;
	

	public User findUser(int id) {
		
		Optional<User> optional= userRepository.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}

	public List<User> findUserByRole(UserRole role) {
		
		return userRepository.findByUserRole(role);
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}
	

}
