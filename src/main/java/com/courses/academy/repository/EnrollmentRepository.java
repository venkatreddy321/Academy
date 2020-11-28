package com.courses.academy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.courses.academy.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {

	@Query("select e from Enrollment e where e.enrollmentId=:enrollmentId")
	Optional<Enrollment> findByEnrollId(@Param("enrollmentId") Integer enrollmentId);

}
