package com.courses.academy.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "enrollment")
public class Enrollment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Integer enrollmentId;
private String userId;
private Integer courseId;
private LocalDate enrollmentDate;
private String enrollmentStatus;
private LocalDate lastUpdated;

public Integer getEnrollmentId() {
	return enrollmentId;
}
public void setEnrollmentId(Integer enrollmentId) {
	this.enrollmentId = enrollmentId;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public Integer getCourseId() {
	return courseId;
}
public void setCourseId(Integer courseId) {
	this.courseId = courseId;
}
public LocalDate getEnrollmentDate() {
	return enrollmentDate;
}
public void setEnrollmentDate(LocalDate enrollmentDate) {
	this.enrollmentDate = enrollmentDate;
}
public String getEnrollmentStatus() {
	return enrollmentStatus;
}
public void setEnrollmentStatus(String enrollmentStatus) {
	this.enrollmentStatus = enrollmentStatus;
}
public LocalDate getLastUpdated() {
	return lastUpdated;
}
public void setLastUpdated(LocalDate lastUpdated) {
	this.lastUpdated = lastUpdated;
}
}
