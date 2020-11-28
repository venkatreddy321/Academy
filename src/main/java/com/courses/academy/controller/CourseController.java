package com.courses.academy.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.courses.academy.dto.CourseResponsesDto;
import com.courses.academy.service.CourseService;
import lombok.extern.slf4j.Slf4j;

/**
 * This controller is handling the requests and responses for Courses
 * operations.
 * 
 * @author SQUAD2
 * @since 2020/11/28
 *
 */
@RestController
@Slf4j
public class CourseController {

	@Autowired
	CourseService courseService;
	/**
	 * Method to call service method to fetch the courses
	 * 
	 * @param No input request is provided for the courses list
	 * @return List<CourseResponsesDto> which consist of list of courses.
	 * @throws CoursesNotFoundException will throw if fetching courses fails. 
	 */
	@GetMapping("/courses")
	public ResponseEntity<Optional<CourseResponsesDto>> showCourses(){
		
		return new ResponseEntity<>(courseService.obtainCourses(), HttpStatus.OK);
	}
}
