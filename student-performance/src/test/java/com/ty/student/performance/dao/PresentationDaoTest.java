package com.ty.student.performance.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ty.student.performance.StudentPerformanceApplication;
import com.ty.student.performance.entity.Presentation;

@SpringBootTest(classes = StudentPerformanceApplication.class)
class PresentationDaoTest {

	@Autowired
	private PresentationDao presentationDao;

	@Disabled
	@Test
	public void savePresentation() {
		Presentation presentation = new Presentation();

		presentation.setTopic("OOPs");
		assertEquals(presentation, presentationDao.savePresentation(presentation));
	}
	
	@Disabled
	@Test
	public void addTrainerMark() {
		Presentation presentation=new Presentation();
		
		presentation.setTrainerId(1);
		presentation.setTrainerMark(4.5);
		assertEquals(presentation, presentationDao.addTrainerMark(presentation));
	}
	
	@Disabled
	@Test
	public void getPresentationByStudentId() {
		List<Presentation> presentations= presentationDao.getPresentationByStudentId(1);
		assertNotNull(presentations);
	}
	
	@Disabled
	@Test
	public void getAllPresentation() {
		List<Presentation> presentations =presentationDao.getAllPresentation();
		assertNotNull(presentations);
	}
	
	@Test
	public void getPresentationByTrainerId() {
		List<Presentation> presentations=presentationDao.getAllPresentationByTrainerId(2);
		assertNotNull(presentations);
	}
}
