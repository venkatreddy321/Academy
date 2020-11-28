package com.courses.academy.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.courses.academy.dto.CourseDto;
import com.courses.academy.dto.EnrollResponseDto;
import com.courses.academy.dto.ResponseDto;
import com.courses.academy.entity.Enrollment;
import com.courses.academy.exception.EnrollmentNotAllowedException;
import com.courses.academy.exception.EnrollmentNotFoundException;
import com.courses.academy.exception.InvalidCourseIdException;
import com.courses.academy.util.CourseStatus;
import com.courses.academy.util.UserConstants;

/**
 * 
 * Service interface to get the course related operations. 
 * The preferred implementation is {@code CourseServiceImpl}.
 * 
 * @author Kiruthika
 * @since 2020/11/28
 *
 */
public interface CourseService {

	/**
	 * Method to enroll the course
	 * 
	 * @param userId   id of the user who is going to enroll.
	 * @param courseId course id to enroll the course .
	 * @return CustomerResponseDto which consist the message ,status code ,
	 *         enrollment id.
	 * @throws InvalidCourseIdException      will throw if the course not valid.
	 * @throws EnrollmentNotAllowedException will throw if -> course start date is
	 *                                       before the current date. -> current
	 *                                       date is less than two days before of
	 *                                       course start date. -> course start date
	 *                                       is after twenty days from current date.
	 *                                       -> user have more than three scheduled
	 *                                       courses.
	 */
	public EnrollResponseDto courseEnrollment(String userId, Integer courseId)
			throws InvalidCourseIdException, EnrollmentNotAllowedException;

	public List<CourseDto> enrollmentList(String userId) throws EnrollmentNotFoundException;
	
	public ResponseDto cancelEnrollment(Integer enrollId) throws EnrollmentNotFoundException;
	
	public ResponseDto editEnrollment(Integer enrollId, Integer courseId)
			throws EnrollmentNotFoundException, InvalidCourseIdException;
	
	/**
	 * Method to validate the enrollment 
	 * -> course start date is before the current
	 * date. 
	 * -> current date is less than two days before of course start date. 
	 * ->course start date is after twenty days from current date.
	 */
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

	/**
	 * Method to validate the enrollment 
	 * -> user have more than three scheduled
	 * courses.
	 */
	default void validateEnrollmentCourseStatus(Optional<List<Enrollment>> enrollments)
			throws EnrollmentNotAllowedException {
		if (enrollments.isPresent()) {
			Long scheduledCourseCount = enrollments.get().stream()
					.filter(enroll -> enroll.getEnrollmentStatus().equals(CourseStatus.SCHEDULED.name())).count();
			if (scheduledCourseCount == 3) {
				throw new EnrollmentNotAllowedException(UserConstants.THREE_CORSES_SCHEDULED);
			}
		}
	}

	
}
