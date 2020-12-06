package com.stacksimply.restservices.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND) // In addition to below return , it will show response as not found i.e 404
	public CustomErrorDetails usernameNotFoundException(UserNotFoundException ex) {
		return new CustomErrorDetails(new Date(),"From GlobalRestControllerAdviceExceptionHandler " , ex.getMessage());
		
	}

}
