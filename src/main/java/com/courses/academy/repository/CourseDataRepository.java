package com.courses.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.courses.academy.entity.CourseData;
@Repository
public interface CourseDataRepository  extends JpaRepository<CourseData, Integer>{

}
