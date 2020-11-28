package com.courses.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courses.academy.entity.CourseData;

public interface CourseDataRepository extends JpaRepository<CourseData, Integer>{

}
