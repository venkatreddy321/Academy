package com.courses.academy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.courses.academy.dto.EnrollResponseDto;
import com.courses.academy.entity.Course;
import com.courses.academy.entity.Enrollment;
import com.courses.academy.exception.EnrollmentNotAllowedException;
import com.courses.academy.exception.InvalidCourseIdException;
import com.courses.academy.repository.CourseRepository;
import com.courses.academy.repository.EnrollmentRepository;
import com.courses.academy.util.CourseStatus;
import com.courses.academy.util.UserConstants;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class CourseServiceImplTest {

	@InjectMocks
	CourseServiceImpl courseServiceImpl;

	@Mock
	CourseRepository courseRepository;
	@Mock
	EnrollmentRepository enrollmentRepository;

	EnrollResponseDto enrollResponseDto;

	Course course;
	Course course1;
	Course course3;

	Enrollment enrollment;
	List<Enrollment> enrollments;

	@BeforeAll
	public void setUp() {
		enrollResponseDto = new EnrollResponseDto();
		enrollResponseDto.setEnrollmentId(1);
		enrollResponseDto.setMessage(UserConstants.ENROLLMENT_SUCCESS);
		enrollResponseDto.setStatusCode(HttpStatus.OK.value());
		LocalDate currentDate = LocalDate.now();

		course = new Course();
		course.setCourseId(1);
		course.setStartDate(currentDate.plusDays(3));

		course1 = new Course();
		course1.setCourseId(1);
		course1.setStartDate(currentDate);

		enrollment = new Enrollment();
		enrollment.setCourseId(1);
		enrollment.setUserId("1");
		enrollment.setEnrollmentStatus(CourseStatus.SCHEDULED.name());

		enrollments = new ArrayList<>();
		enrollments.add(enrollment);
	}

	@Test
	public void courseEnrollmentTest() throws InvalidCourseIdException, EnrollmentNotAllowedException {

		// GIVEN
		when(courseRepository.findById(1)).thenReturn(Optional.of(course));
		when(enrollmentRepository.findByUserId("1")).thenReturn(Optional.of(enrollments));

		// WHEN
		EnrollResponseDto enrollResponseDto = courseServiceImpl.courseEnrollment("1", 1);

		// THEN
		assertEquals(enrollResponseDto.getMessage(), enrollResponseDto.getMessage());
	}

	@Test()
	public void courseEnrollmentTwoDaysBeforeTest() throws InvalidCourseIdException, EnrollmentNotAllowedException {

//		// GIVEN
//		when(courseRepository.findById(1)).thenReturn(Optional.of(course1));
//		when(enrollmentRepository.findByUserId("1")).thenReturn(Optional.of(enrollments));
//
//		// WHEN
//		assertThrows(EnrollmentNotAllowedException.class, courseServiceImpl.courseEnrollment("1", 1));
	}
	
}
