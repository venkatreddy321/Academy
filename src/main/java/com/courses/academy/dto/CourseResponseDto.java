package com.courses.academy.dto;

import java.time.LocalDate;

public class CourseResponseDto {
	private String courseCode;
	private String courseName;
	private Integer courseId;
	private String trainerName;
	private String trainingDuration;
	private LocalDate startDate;
	private LocalDate endDate;
	

	
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTrainerName() {
		return trainerName;
	}
	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}
	public String getTrainingDuration() {
		return trainingDuration;
	}
	public void setTrainingDuration(String trainingDuration) {
		this.trainingDuration = trainingDuration;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
}
