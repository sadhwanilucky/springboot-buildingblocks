package com.stacksimply.restservices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.stacksimply.restservices.entities.User;
import com.stacksimply.restservices.entities.Views;
import com.stacksimply.restservices.exceptions.UserNotFoundException;
import com.stacksimply.restservices.services.UserService;

@RestController // To communicate with CLient/Browser/Rest Client
@Validated
@RequestMapping(value="/jsonview/users")
public class UserJsonViewController {
	
	@Autowired
	private UserService userService;
	
	//getUserbyID External
	@GetMapping("/external/{id}")
	@JsonView(Views.External.class)
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {// It means value should be atleast 1 or greater
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());

		}
	}
	
	//getUserbyID Internal
	@GetMapping("/internal/{id}")
	@JsonView(Views.Internal.class)
	public Optional<User> getUserById2(@PathVariable("id") @Min(1) Long id) {// It means value should be atleast 1 or greater
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());

		}
	}

}
