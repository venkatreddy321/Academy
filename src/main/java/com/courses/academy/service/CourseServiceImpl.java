package com.courses.academy.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.courses.academy.dto.CourseDto;
import com.courses.academy.dto.CourseResponseDto;
import com.courses.academy.dto.CourseResponsesDto;
import com.courses.academy.dto.EnrollResponseDto;
import com.courses.academy.dto.ResponseDto;
import com.courses.academy.entity.Course;
import com.courses.academy.entity.CourseData;
import com.courses.academy.entity.Enrollment;
import com.courses.academy.exception.CoursesNotFoundException;
import com.courses.academy.exception.EnrollmentNotAllowedException;
import com.courses.academy.exception.EnrollmentNotFoundException;
import com.courses.academy.exception.InvalidCourseIdException;
import com.courses.academy.repository.CourseDataRepository;
import com.courses.academy.repository.CourseRepository;
import com.courses.academy.repository.EnrollmentRepository;
import com.courses.academy.repository.UserRepository;
import com.courses.academy.util.CourseStatus;
import com.courses.academy.util.UserConstants;

/**
 * Implementation of CourseService which will give the course related
 * operations.
 * 
 * @author Kiruthika && prem
 * @since 2020/11/28
 */
@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository courseRepository;
	@Autowired
	EnrollmentRepository enrollmentRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CourseDataRepository courseDataRepository;

	/**
	 * Method to enroll the course
	 * 
	 * @param userId   id of the user who is going to enroll.
	 * @param courseId course id to enroll the course .
	 * @return CustomerResponseDto which consist the message ,status code ,
	 *         enrollment id.
	 * @throws InvalidCourseIdException      will throw if the course not valid.
	 * @throws EnrollmentNotAllowedException will throw if -> course start date is
	 *                                       before the current date. -> current
	 *                                       date is less than two days before of
	 *                                       course start date. -> course start date
	 *                                       is after twenty days from current date.
	 *                                       -> user have more than three scheduled
	 *                                       courses.
	 */
	public EnrollResponseDto courseEnrollment(String userId, Integer courseId)
			throws InvalidCourseIdException, EnrollmentNotAllowedException {

		Optional<Course> course = courseRepository.findById(courseId);
		if (!course.isPresent()) {
			throw new InvalidCourseIdException(UserConstants.INVALID_COURSE);
		}

		LocalDate currentDate = LocalDate.now();

		validateEnrollment(course.get().getStartDate());

		Optional<List<Enrollment>> enrollments = enrollmentRepository.findByUserId(userId);

		validateEnrollmentCourseStatus(enrollments);

		Enrollment enroll = new Enrollment();
		enroll.setCourseId(courseId);
		enroll.setEnrollmentDate(currentDate);
		enroll.setEnrollmentStatus(CourseStatus.SCHEDULED.name());
		enroll.setUserId(userId);
		enroll.setLastUpdated(currentDate);
		enrollmentRepository.save(enroll);

		EnrollResponseDto enrollResponseDto = new EnrollResponseDto();
		enrollResponseDto.setEnrollmentId(enroll.getEnrollmentId());
		enrollResponseDto.setMessage(UserConstants.ENROLLMENT_SUCCESS);
		enrollResponseDto.setStatusCode(HttpStatus.OK.value());

		return enrollResponseDto;

	}

	public List<CourseDto> enrollmentList(String userId) throws EnrollmentNotFoundException {

		Optional<List<Enrollment>> enrollmentList = enrollmentRepository.findByUserId(userId);

		CourseDto courseDto = new CourseDto();

		if (!enrollmentList.isPresent()) {
			throw new EnrollmentNotFoundException(UserConstants.ENROLLMENT_NOT_FOUND);
		}
		return enrollmentList.get().stream().map(enroll -> {
			Optional<CourseData> courseData = courseDataRepository.findById(enroll.getCourseId());
			courseDto.setCourseId(courseData.get().getCourseId());
			courseDto.setCourseCode(courseData.get().getCourseCode());
			courseDto.setCourseName(courseData.get().getCourseName());
			courseDto.setEnrollmentId(enroll.getEnrollmentId());
			courseDto.setEnrollmentStatus(enroll.getEnrollmentStatus());
			return courseDto;
		}).collect(Collectors.toList());

	}

	public ResponseDto cancelEnrollment(Integer enrollId) throws EnrollmentNotFoundException {

		Optional<Enrollment> enrollment = enrollmentRepository.findById(enrollId);
		if (!enrollment.isPresent()) {
			throw new EnrollmentNotFoundException(UserConstants.ENROLLMENT_NOT_FOUND);
		}
		if (enrollment.get().getEnrollmentStatus().equals(CourseStatus.SCHEDULED.name())) {
			enrollment.get().setEnrollmentStatus(CourseStatus.CANCELLED.name());
			enrollmentRepository.save(enrollment.get());
		} else {
			throw new EnrollmentNotFoundException(
					UserConstants.ENROLLMENT_CAN_NOT_CANCELLED + enrollment.get().getEnrollmentStatus());
		}
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(UserConstants.ENROLLMENT_CANCELLED);
		responseDto.setStatus(HttpStatus.OK.value());
		return responseDto;

	}

	public ResponseDto editEnrollment(Integer enrollId, Integer courseId)
			throws EnrollmentNotFoundException, InvalidCourseIdException {

		Optional<Enrollment> enrollment = enrollmentRepository.findById(enrollId);
		if (!enrollment.isPresent()) {
			throw new EnrollmentNotFoundException(UserConstants.ENROLLMENT_NOT_FOUND);
		}
		if (!StringUtils.isEmpty(courseId)) {
			Optional<CourseData> courseData = courseDataRepository.findById(courseId);

			if (!courseData.isPresent()) {
				throw new InvalidCourseIdException(UserConstants.INVALID_COURSE);
			}
			if (enrollment.get().getEnrollmentStatus().equals(CourseStatus.SCHEDULED.name())) {
				enrollment.get().setCourseId(courseId);
				enrollment.get().setLastUpdated(LocalDate.now());
				enrollmentRepository.save(enrollment.get());
			} else {
				throw new EnrollmentNotFoundException(
						UserConstants.ENROLLMENT_CAN_NOT_CANCELLED + enrollment.get().getEnrollmentStatus());
			}

		}

		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(UserConstants.ENROLLMENT_UPDATE_SUCCESS);
		responseDto.setStatus(HttpStatus.OK.value());
		return responseDto;

	}

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
		if (CollectionUtils.isEmpty(coursesList)) {
			throw new CoursesNotFoundException(UserConstants.COURSES_NOT_FOUND);
		}
		for (int i = 0; i < coursesList.size(); i++) {
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

		courseResponsesDto.setMessage(UserConstants.COURSE_DETAILS);
		courseResponsesDto.setStatusCode(200);
		courseResponsesDto.setCoursesList(courseResponseDtoList);

		return Optional.of(courseResponsesDto);

	}
}
