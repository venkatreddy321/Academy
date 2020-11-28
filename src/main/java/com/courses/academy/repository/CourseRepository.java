package com.courses.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.courses.academy.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{

}
