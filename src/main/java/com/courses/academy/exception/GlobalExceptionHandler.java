package com.courses.academy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/*
	 * @ExceptionHandler(Exception.class) public ResponseEntity<ErrorResponse>
	 * exceptionHandler(Exception exception, WebRequest request) { ErrorResponse
	 * errorResponse = new ErrorResponse(exception.getMessage(),
	 * HttpStatus.INTERNAL_SERVER_ERROR.value()); return new
	 * ResponseEntity<>(errorResponse, HttpStatus.OK); }
	 * 
	 * @ExceptionHandler(EnrollmentNotFoundException.class) public
	 * ResponseEntity<ErrorResponse>
	 * enrollmentNotFoundExceptionHandler(EnrollmentNotFoundException exception,
	 * WebRequest request) { ErrorResponse errorResponse = new
	 * ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value() );
	 * return new ResponseEntity<>(errorResponse, HttpStatus.OK);
	 * 
	 * }
	 */
}
