package com.courses.academy.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseUpdatRequesteDto {

	private Integer newCourseId;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate newStratDate;

	public Integer getNewCourseId() {
		return newCourseId;
	}

	public void setNewCourseId(Integer newCourseId) {
		this.newCourseId = newCourseId;
	}

	public LocalDate getNewStratDate() {
		return newStratDate;
	}

	public void setNewStratDate(LocalDate newStratDate) {
		this.newStratDate = newStratDate;
	}

}
