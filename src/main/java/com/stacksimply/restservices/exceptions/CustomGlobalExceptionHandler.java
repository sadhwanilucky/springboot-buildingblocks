package com.stacksimply.restservices.exceptions;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // Means that it will be applicable to all the controllers
//Please uncommment, Has been commented to test GlobalRestControllerAdviceExceptionHandler
//First priority is given to @ControllerAdvice as compared to @RestControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// MethodArgumentNotValidException handling
	// Reason for overriding the handleMethodArgumentNotValid is because otherwise
	// default implementation will display extra information in error response.
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails CustomErrorDetails = new CustomErrorDetails(new Date(),
				"From Method Argument Not valid in Global Exceptional Handler", ex.getMessage());
		return new ResponseEntity<Object>(CustomErrorDetails, HttpStatus.BAD_REQUEST);
	}

	// HttpRequestMethodNotSupportedException handling
	// Base Definition is in ResponseEntityExceptionHandler.
	// First Goto ResponseEntityExceptionHandler then search for
	// HttpRequestMethodNotSupportedException then check for the implemented method
	// definition and then override
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails CustomErrorDetails = new CustomErrorDetails(new Date(),
				"From HttpRequestMethodNotSupportedException in Global Exceptional Handler - Method not allowed",
				ex.getMessage());
		return new ResponseEntity<Object>(CustomErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	//UserNotFoundException handling
	//As user defined so no need to overide****
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex,
			 WebRequest request){
		CustomErrorDetails CustomErrorDetails = new CustomErrorDetails(new Date(),
				"From HttpRequestMethodNotSupportedException in Global Exceptional Handler - Method not allowed",
				request.getDescription(false));//If true then we will get client info as well
		return new ResponseEntity<Object>(CustomErrorDetails, HttpStatus.NOT_FOUND);
	}

	
	//ConstraintViolationException
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			 WebRequest request){
		CustomErrorDetails CustomErrorDetails = new CustomErrorDetails(new Date(),
				ex.getMessage(),
				request.getDescription(false));//If true then we will get client info as well
		return new ResponseEntity<Object>(CustomErrorDetails, HttpStatus.BAD_REQUEST);
		
	}
	
}
