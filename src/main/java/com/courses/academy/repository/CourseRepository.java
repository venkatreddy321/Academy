package com.courses.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.courses.academy.entity.Course;
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

	//@Query("select new com.courses.academy.dto.CourseResponseDto(c.course_id as courseId, c.trainer_name as trainerName,c.start_date as startDate,c.end_date as endDate,c.training_duration as trainingDuration,cd.course_name as courseName,cd.course_code as courseCode) from academy.course c join academy.coursedata cd on c.courseId=cd.courseId")
	//@Query("select c.course_id, c.trainer_name,cd.course_name from academy.course c join academy.coursedata cd on c.course_id=cd.course_id") 
	//List<CourseResponseDto> findAllCourses();
	
	

}
