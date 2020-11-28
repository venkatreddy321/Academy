package com.courses.academy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.courses.academy.dto.CourseResponseDto;
import com.courses.academy.dto.CourseResponsesDto;
import com.courses.academy.entity.Course;
import com.courses.academy.entity.CourseData;
import com.courses.academy.exception.CoursesNotFoundException;
import com.courses.academy.repository.CourseDataRepository;
import com.courses.academy.repository.CourseRepository;
import com.courses.academy.util.CourseConstants;
/**
 * Implementation of CourseService which will retrieve the courses.
 * 
 * @author SQUAD2
 * @since 2020/11/28
 */
@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	CourseDataRepository courseDataRepository;
	/**
	 * Method to fetch the courses
	 * 
	 * @param no input is provided here
	 * @return List<CourseResponsesDto> which consist of list of Courses.
	 * @throws CoursesNotFoundException will throw if fetching courses fails.
	 */
	@Override
	public Optional<CourseResponsesDto> obtainCourses() {
		
		List<Course> coursesList = courseRepository.findAll();
		List<CourseData> coursesDataList = courseDataRepository.findAll();
		List<CourseResponseDto> courseResponseDtoList = new ArrayList<>();
		if(CollectionUtils.isEmpty(coursesList)) {
			throw new CoursesNotFoundException(CourseConstants.COURSES_NOT_FOUND);
		}
		for(int i=0;i<coursesList.size();i++) {
			CourseResponseDto courseResponseDto = new CourseResponseDto();
			Course course = coursesList.get(i);
			CourseData courseData = coursesDataList.get(i);
			courseResponseDto.setCourseId(course.getCourseId());
			courseResponseDto.setCourseCode(courseData.getCourseCode());
			courseResponseDto.setCourseName(courseData.getCourseName());
			courseResponseDto.setEndDate(course.getEndDate());
			courseResponseDto.setStartDate(course.getStartDate());
			courseResponseDto.setTrainerName(course.getTrainerName());
			courseResponseDto.setTrainingDuration(course.getTrainingDuration());
			courseResponseDtoList.add(courseResponseDto);
		
		}
		CourseResponsesDto courseResponsesDto = new CourseResponsesDto();
		
				courseResponsesDto.setMessage(CourseConstants.COURSE_DETAILS);
				courseResponsesDto.setStatusCode(200);			
				courseResponsesDto.setCoursesList(courseResponseDtoList);
		
			return Optional.of(courseResponsesDto);
		
		 
		//List<CourseResponseDto> coursesList = courseRepository.findAllCourses();
//if(!coursesList.isPresent()){
//	throw new CoursesNotFoundException(CourseConstants.COURSES_NOT_FOUND);
//}
//		List<CourseResponseDto> courseList = coursesList.get().stream().map(courses -> {
//			CourseResponseDto courseResponseDto = new CourseResponseDto();
//			BeanUtils.copyProperties(courseResponseDto,courses);
//			return courseResponseDto;
//		}).collect(Collectors.toList());
//		CourseResponsesDto courseResponsesDto = new CourseResponsesDto();
//
//		courseResponsesDto.setMessage(CourseConstants.COURSE_DETAILS);
//		courseResponsesDto.setStatusCode(200);
//		courseResponsesDto.setCoursesList(courseList);
//
//		return Optional.of(courseResponsesDto);
		
	}
}
