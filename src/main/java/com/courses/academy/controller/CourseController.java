package com.courses.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.courses.academy.dto.EnrollResponseDto;
import com.courses.academy.exception.EnrollmentNotAllowedException;
import com.courses.academy.exception.InvalidCourseIdException;
import com.courses.academy.service.CourseService;

@RestController
@RequestMapping("/api")
public class CourseController {

	@Autowired
	CourseService courseService;

	@PostMapping("/user/{userId}/enroll")
	public ResponseEntity<EnrollResponseDto> courseEnrollment(@PathVariable String userId,
			@RequestParam Integer courseId) throws InvalidCourseIdException, EnrollmentNotAllowedException {

		EnrollResponseDto enrollResponseDto = courseService.courseEnrollment(userId, courseId);

		return new ResponseEntity<>(enrollResponseDto, HttpStatus.OK);
	}

}
