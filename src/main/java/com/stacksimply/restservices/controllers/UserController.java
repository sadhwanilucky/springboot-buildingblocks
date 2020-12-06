package com.stacksimply.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimply.restservices.entities.User;
import com.stacksimply.restservices.exceptions.UserExistException;
import com.stacksimply.restservices.exceptions.UserNotFoundException;
import com.stacksimply.restservices.services.UserService;

@RestController // To communicate with CLient/Browser/Rest Client
@Validated
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

//	The HTTP Location header is a response header
//	that is used under 2 circumstances to ask a browser to redirect a URL (status code 3xx) 
//	or provide information about the location of a newly created resource (status code of 201)

	@PostMapping
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user,UriComponentsBuilder builder) {
		try {
		 userService.createUser(user);
		 HttpHeaders header = new HttpHeaders();
		 header.setLocation(builder.path("users/{id}").buildAndExpand(user.getUserid()).toUri());//returns the location i.e uri of user created,buildAndExpand simply passing the value of variable i.e id
		 return new ResponseEntity<Void>(header,HttpStatus.CREATED);
		}catch (UserExistException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
	}

	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {// It means value should be atleast 1 or greater
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());

		}

	}

	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id")  Long id, @RequestBody User user) { 
		try {
			return userService.updateUserById(id, user);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public void deleteUserByid(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}

	@GetMapping("/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) throws UserNotFoundException {
		User user = userService.getUserByUserName(username);
		if(user ==null) {
			throw new UserNotFoundException("Username : '"+username+"' not found in user repository");
		}else {
			return user;
		}
	}
}
