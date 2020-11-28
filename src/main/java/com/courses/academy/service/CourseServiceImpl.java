package com.courses.academy.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.courses.academy.dto.EnrollResponseDto;
import com.courses.academy.entity.Course;
import com.courses.academy.entity.Enrollment;
import com.courses.academy.exception.EnrollmentNotAllowedException;
import com.courses.academy.exception.InvalidCourseIdException;
import com.courses.academy.repository.CourseRepository;
import com.courses.academy.repository.EnrollmentRepository;
import com.courses.academy.util.UserConstants;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository courseRepository;
	@Autowired
	EnrollmentRepository enrollmentRepository;

	public EnrollResponseDto courseEnrollment(String userId, Integer courseId)
			throws InvalidCourseIdException, EnrollmentNotAllowedException {

		Optional<Course> course = courseRepository.findById(courseId);
		if (!course.isPresent()) {
			throw new InvalidCourseIdException(UserConstants.INVALID_COURSE);
		}

		LocalDate currentDate = LocalDate.now();

		validateEnrollment(course.get().getStartDate());

		Optional<List<Enrollment>> enrollments = enrollmentRepository.findByUserId(userId);

		validateEnrollmentCourseStatus(enrollments);

		Enrollment enroll = new Enrollment();
		enroll.setCourseId(courseId);
		enroll.setEnrollmentDate(currentDate);
		enroll.setEnrollmentStatus("SCHEDULED");
		enroll.setUserId(userId);
		enroll.setLastUpdated(currentDate);
		enrollmentRepository.save(enroll);

		EnrollResponseDto enrollResponseDto = new EnrollResponseDto();
		enrollResponseDto.setEnrollmentId(enroll.getEnrollmentId());
		enrollResponseDto.setMessage(UserConstants.ENROLLMENT_SUCCESS);
		enrollResponseDto.setStatusCode(HttpStatus.OK.value());

		return enrollResponseDto;

	}

}
