package com.courses.academy.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.courses.academy.dto.EnrollResponseDto;
import com.courses.academy.entity.Enrollment;
import com.courses.academy.exception.EnrollmentNotAllowedException;
import com.courses.academy.exception.InvalidCourseIdException;
import com.courses.academy.util.UserConstants;

public interface CourseService {

	public EnrollResponseDto courseEnrollment(String userId, Integer courseId)
			throws InvalidCourseIdException, EnrollmentNotAllowedException;

	default void validateEnrollment(LocalDate courseStartDate) throws EnrollmentNotAllowedException {

		LocalDate currentDate = LocalDate.now();

		if (courseStartDate.isBefore(currentDate)) {
			throw new EnrollmentNotAllowedException(UserConstants.COURSE_ALREADY_STARTED);
		}
		if (!courseStartDate.isAfter(currentDate.plusDays(2))) {
			throw new EnrollmentNotAllowedException(UserConstants.COURSE_STARTING_TWO_DAYS);
		}
		if (courseStartDate.isAfter(currentDate.plusDays(20))) {
			throw new EnrollmentNotAllowedException(UserConstants.COURSE_START_AFTER_TWENTY_DAYS);
		}
	}

	default void validateEnrollmentCourseStatus(Optional<List<Enrollment>> enrollments)
			throws EnrollmentNotAllowedException {
		if (enrollments.isPresent()) {
			Long scheduledCourseCount = enrollments.get().stream()
					.filter(enroll -> enroll.getEnrollmentStatus().equals("SCHEDULED")).count();
			if (scheduledCourseCount == 3) {
				throw new EnrollmentNotAllowedException(UserConstants.THREE_CORSES_SCHEDULED);
			}
		}
	}

}
