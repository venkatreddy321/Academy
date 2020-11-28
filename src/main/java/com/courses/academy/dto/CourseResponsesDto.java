package com.courses.academy.dto;
import java.util.List;

public class CourseResponsesDto {

	private List<CourseResponseDto> coursesList;
	private String message;
	private Integer statusCode;
	
	public List<CourseResponseDto> getCoursesList() {
		return coursesList;
	}
	public void setCoursesList(List<CourseResponseDto> coursesList) {
		this.coursesList = coursesList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
}