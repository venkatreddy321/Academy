package com.courses.academy.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courses.academy.dto.CourseUpdatRequesteDto;
import com.courses.academy.dto.UpdateResponseDto;
import com.courses.academy.entity.Enrollment;
import com.courses.academy.exception.EnrollmentNotFoundException;
import com.courses.academy.repository.EnrollmentRepository;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

	@Autowired
	EnrollmentRepository enrollmentRepository;

	@Override
	public Optional<UpdateResponseDto> updateEnrollment(Integer enrollId,
			CourseUpdatRequesteDto courseUpdatRequesteDto) {

		Optional<Enrollment> enrollmentOpt1 = enrollmentRepository.findByEnrollId(enrollId);
		if (!enrollmentOpt1.isPresent()) {
			throw new EnrollmentNotFoundException("No Enrollment found");
		}
		System.out.println("checking the enrollId.." + enrollId);
		String message;
		Integer statusCode = 200;
		if (enrollmentOpt1.isPresent()) {
			
			Enrollment enrollment = enrollmentOpt1.get();
			LocalDate d = LocalDate.now().minusDays(2);
			if(null==enrollment.getEnrollmentDate() || enrollment.getEnrollmentDate().isBefore(d)) {
				statusCode = 333;
				message = "Cannot update this enrollment";
			}
			if (null != courseUpdatRequesteDto.getNewStratDate()) {
				enrollment.setCourseId(courseUpdatRequesteDto.getNewCourseId());
			} else {
				enrollment.setCourseId(courseUpdatRequesteDto.getNewCourseId());
				enrollment.setEnrollmentStatus("SCHEDULED");
			}
			enrollment.setLastUpdated(LocalDate.now());
			enrollmentRepository.save(enrollment);
			statusCode = 201;
			message = "Successfully Updated";
		} else {
			statusCode = 404;
			message = "No enrollment found";
		}
		UpdateResponseDto updateResponseDTO = new UpdateResponseDto();
		updateResponseDTO.setMessage(message);
		updateResponseDTO.setStatusCode(statusCode);

		return Optional.of(updateResponseDTO);
	}
}
