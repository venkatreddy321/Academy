package com.courses.academy.service;

import java.util.Optional;

import com.courses.academy.dto.CourseUpdatRequesteDto;
import com.courses.academy.dto.UpdateResponseDto;

public interface EnrollmentService {
	public Optional<UpdateResponseDto> updateEnrollment(Integer enrollId,
			CourseUpdatRequesteDto courseUpdatRequesteDto);
}
