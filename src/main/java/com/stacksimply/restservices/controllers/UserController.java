package com.stacksimply.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimply.restservices.entities.User;
import com.stacksimply.restservices.exceptions.UserExistException;
import com.stacksimply.restservices.exceptions.UserNotFoundException;
import com.stacksimply.restservices.services.UserService;

@RestController // To communicate with CLient/Browser/Rest Client
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

//	The HTTP Location header is a response header
//	that is used under 2 circumstances to ask a browser to redirect a URL (status code 3xx) 
//	or provide information about the location of a newly created resource (status code of 201)

	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@RequestBody User user,UriComponentsBuilder builder) {
		try {
		 userService.createUser(user);
		 HttpHeaders header = new HttpHeaders();
		 header.setLocation(builder.path("users/{id}").buildAndExpand(user.getId()).toUri());//returns the location i.e uri of user created,buildAndExpand simply passing the value of variable i.e id
		 return new ResponseEntity<Void>(header,HttpStatus.CREATED);
		}catch (UserExistException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
	}

	@GetMapping("users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id) {
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());

		}

	}

	@PutMapping("users/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			return userService.updateUserById(id, user);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	@DeleteMapping("users/{id}")
	public void deleteUserByid(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}

	@GetMapping("users/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		return userService.getUserByUserName(username);
	}
}
