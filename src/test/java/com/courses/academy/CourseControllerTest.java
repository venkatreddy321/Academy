package com.courses.academy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.courses.academy.controller.CourseController;
import com.courses.academy.dto.CourseUpdatRequesteDto;
import com.courses.academy.dto.UpdateResponseDto;
import com.courses.academy.service.EnrollmentService;

@ExtendWith(MockitoExtension.class)

@TestInstance(Lifecycle.PER_CLASS)
public class CourseControllerTest {

	@Mock
	EnrollmentService enrollmentService;

	@InjectMocks
	CourseController courseController;

	CourseUpdatRequesteDto courseUpdatRequesteDto;
	UpdateResponseDto updateResponseDto;
	Integer enrollId;

	CourseUpdatRequesteDto courseUpdatRequesteDto1;
	UpdateResponseDto updateResponseDto1;
	Integer enrollId1;

	@BeforeAll
	public void setUp() {
		enrollId = 1001;
		courseUpdatRequesteDto = new CourseUpdatRequesteDto();
		courseUpdatRequesteDto.setNewCourseId(1001);
		courseUpdatRequesteDto.setNewStratDate(LocalDate.now());

		updateResponseDto = new UpdateResponseDto();
		updateResponseDto.setMessage("Successfully Updated");
		updateResponseDto.setStatusCode(201);

		enrollId1 = 1002;
		courseUpdatRequesteDto1 = new CourseUpdatRequesteDto();
		courseUpdatRequesteDto1.setNewCourseId(1001);
		courseUpdatRequesteDto1.setNewStratDate(LocalDate.now());

		updateResponseDto1 = new UpdateResponseDto();
		updateResponseDto1.setMessage("Cannot update this enrollment");
		updateResponseDto1.setStatusCode(201);

	}

	@Test
	public void successUpdateTest() {

		// Given
		Mockito.when(enrollmentService.updateEnrollment(enrollId, courseUpdatRequesteDto))
				.thenReturn(Optional.of(updateResponseDto));
		// when
		ResponseEntity<UpdateResponseDto> result = courseController.updateEnrollmentDetails(enrollId,
				courseUpdatRequesteDto);
		// then
		assertEquals("Successfully Updated", result.getBody().getMessage());
	}

	@Test
	public void dateValidationTest() {

		// Given
		Mockito.when(enrollmentService.updateEnrollment(enrollId, courseUpdatRequesteDto1))
				.thenReturn(Optional.of(updateResponseDto1));
		// when
		ResponseEntity<UpdateResponseDto> result = courseController.updateEnrollmentDetails(enrollId,
				courseUpdatRequesteDto1);
		// then
		assertEquals("Cannot update this enrollment", result.getBody().getMessage());
	}

}
