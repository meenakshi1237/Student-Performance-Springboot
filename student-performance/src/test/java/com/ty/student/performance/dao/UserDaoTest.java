package com.ty.student.performance.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ty.student.performance.StudentPerformanceApplication;
import com.ty.student.performance.entity.User;
import com.ty.student.performance.util.UserRole;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest(classes =StudentPerformanceApplication.class )
class UserDaoTest {
	
	@Autowired
	private UserDao userDao;
	
	@Disabled
	@Test
	public void saveUser() {
		User student=new User();
		
		student.setUserName("karthi");
		student.setEmail("email@gmail.com");
		student.setPassword("Karthi123");
		student.setUserRole(UserRole.STUDENT);
		assertEquals(student,userDao.saveUser(student));
	}
	
	
	@Test
	public void saveUserWithException() {
		User student=new User();
		
		student.setUserName("ka");
		student.setEmail("email@gmail.com");
		student.setPassword("Karthi123");
		student.setUserRole(UserRole.STUDENT);
		assertThrows(ConstraintViolationException.class, () -> userDao.saveUser(student));
	}
	
	@Disabled
	@Test
	public void findUserByRole() {
		List<User> users=userDao.findUserByRole(UserRole.STUDENT);
		assertNotNull(users);
	}
	
	@Disabled
	@Test
	public void findUserById() {
		User user=userDao.findUserById(4);
		assertNotNull(user);
	}
	
	@Disabled
	@Test
	public void findUserByIdNotExist() {
		User user=userDao.findUserById(10);
		assertNull(user);
	}
	
	@Test
	public void deleteStudent() {
		User user=userDao.findUserById(4);
		assertTrue(userDao.deleteStudent(user));
		
	}
	
	@Test
	public void deleteStudentFailed() {
		User user=userDao.findUserById(4);
		assertFalse(userDao.deleteStudent(user));
		
	}

}
