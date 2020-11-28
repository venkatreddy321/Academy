package com.courses.academy.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.courses.academy.dto.EnrollResponseDto;
import com.courses.academy.exception.EnrollmentNotAllowedException;
import com.courses.academy.exception.InvalidCourseIdException;
import com.courses.academy.service.CourseService;
import com.courses.academy.util.UserConstants;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class CourseControllerTest {

	@InjectMocks
	CourseController courseController;
	@Mock
	CourseService courseService;
	
	EnrollResponseDto enrollResponseDto;
	@BeforeAll
	public void setUp() {
		enrollResponseDto=new EnrollResponseDto();
		enrollResponseDto.setEnrollmentId(1);
		enrollResponseDto.setMessage(UserConstants.ENROLLMENT_SUCCESS);
		enrollResponseDto.setStatusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void courseEnrollmentTest() throws InvalidCourseIdException, EnrollmentNotAllowedException {
		//GIVEN
		when(courseService.courseEnrollment("1", 1)).thenReturn(enrollResponseDto);
		
		//WHEN
		ResponseEntity<EnrollResponseDto> actual=courseController.courseEnrollment("1", 1);
		
		//THEN
		assertEquals(enrollResponseDto.getMessage(),actual.getBody().getMessage());
	}
}
