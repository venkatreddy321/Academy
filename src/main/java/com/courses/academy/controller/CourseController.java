package com.courses.academy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.courses.academy.dto.CourseDto;
import com.courses.academy.dto.EnrollResponseDto;
import com.courses.academy.dto.ResponseDto;
import com.courses.academy.exception.EnrollmentNotAllowedException;
import com.courses.academy.exception.EnrollmentNotFoundException;
import com.courses.academy.exception.InvalidCourseIdException;
import com.courses.academy.service.CourseService;

/**
 * Controller for handling the requests and responses related with courses.
 * 
 * @author Kiruthika & Prem
 * @since 2020/11/28
 *
 */
@RestController
@RequestMapping("/api")
public class CourseController {

	@Autowired
	CourseService courseService;

	/**
	 * Method to call service method to enroll the course
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
	@PostMapping("/user/{userId}/enroll")
	public ResponseEntity<EnrollResponseDto> courseEnrollment(@PathVariable String userId,
			@RequestParam Integer courseId) throws InvalidCourseIdException, EnrollmentNotAllowedException {

		EnrollResponseDto enrollResponseDto = courseService.courseEnrollment(userId, courseId);

		return new ResponseEntity<>(enrollResponseDto, HttpStatus.OK);
	}

	@GetMapping("/users/{userId}/enroll")
	public ResponseEntity<List<CourseDto>> enrollmentList(@PathVariable String userId) {

		List<CourseDto> courseDtos = courseService.enrollmentList(userId);

		return new ResponseEntity<>(courseDtos, HttpStatus.OK);
	}

	@DeleteMapping("/enroll/{enrollId}")
	public ResponseEntity<ResponseDto> cancelEnrollment(@PathVariable Integer enrollId)
			throws EnrollmentNotFoundException {

		return new ResponseEntity<>(courseService.cancelEnrollment(enrollId), HttpStatus.OK);

	}
	
//	@PutMapping("/enroll/{enrollId}")
//	public ResponseEntity<T> editEnrollment(@PathVariable Integer enrollId)
}
