package com.stacksimply.restservices.exceptions;

import java.util.Date;

//Create Simple Custom Error Message Bean
public class CustomErrorDetails {
	private Date timestamp;
	private String message;
	private String errorDetails;
	
	public CustomErrorDetails(Date timestamp, String message, String errorDetails) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.errorDetails = errorDetails;
	}

	//Only Getters
	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getErrorDetails() {
		return errorDetails;
	}
	
	

}
