package com.courses.academy.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.courses.academy.dto.CourseResponsesDto;

@Service
public interface CourseService {

	public Optional<CourseResponsesDto> obtainCourses();

}
