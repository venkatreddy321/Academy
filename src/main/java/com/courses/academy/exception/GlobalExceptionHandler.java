package com.courses.academy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception exception, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.OK);
	}

	@ExceptionHandler(InvalidCourseIdException.class)
	public ResponseEntity<ErrorResponse> invalidCourseExceptionHandler(InvalidCourseIdException exception, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value()
				);
		return new ResponseEntity<>(errorResponse, HttpStatus.OK);

	}
	@ExceptionHandler(EnrollmentNotAllowedException.class)
	public ResponseEntity<ErrorResponse> enrollmentNotAllowedExceptionHandler(EnrollmentNotAllowedException exception, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value()
				);
		return new ResponseEntity<>(errorResponse, HttpStatus.OK);

	}
	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<ErrorResponse> invalidUserExceptionHandler(InvalidUserException exception, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value()
				);
		return new ResponseEntity<>(errorResponse, HttpStatus.OK);

	}
}
