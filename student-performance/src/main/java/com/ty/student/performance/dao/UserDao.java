package com.ty.student.performance.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.student.performance.entity.User;
import com.ty.student.performance.repository.UserRepository;

@Repository
public class UserDao {
	
	@Autowired
	private UserRepository userRepository;
	
<<<<<<< HEAD
	public User findUser(int id) {
		
		Optional<User> optional= userRepository.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}

	public List<User> findUserByRole(String role) {
		
		return userRepository.findByRole(role);
	}
=======
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	
>>>>>>> fc0ddb9727a98d8bd3f26d14f3b5b5a45a60a910
}
