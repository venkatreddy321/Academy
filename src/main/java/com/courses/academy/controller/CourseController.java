package com.courses.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.courses.academy.dto.CourseUpdatRequesteDto;
import com.courses.academy.dto.UpdateResponseDto;
import com.courses.academy.service.EnrollmentService;
import com.sun.istack.NotNull;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class CourseController {

	@Autowired
	EnrollmentService enrollmentService;

	@ApiOperation(value = "User can update the course enrolment", response = String.class)
	@PutMapping("/enroll/{id}")
	public ResponseEntity<UpdateResponseDto> updateEnrollmentDetails(@PathVariable @NotNull Integer id,
			@RequestBody CourseUpdatRequesteDto courseUpdatRequesteDto) {
		return new ResponseEntity<UpdateResponseDto>(enrollmentService.updateEnrollment(id, courseUpdatRequesteDto).get(),
				HttpStatus.OK);
	}

	public EnrollmentService getEnrollmentService() {
		return enrollmentService;
	}

	public void setEnrollmentService(EnrollmentService enrollmentService) {
		this.enrollmentService = enrollmentService;
	}

}
