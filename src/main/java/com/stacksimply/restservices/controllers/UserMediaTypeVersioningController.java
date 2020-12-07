package com.stacksimply.restservices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimply.restservices.dtos.UserDtoV1;
import com.stacksimply.restservices.dtos.UserDtoV2;
import com.stacksimply.restservices.dtos.UserMMDto;
import com.stacksimply.restservices.entities.User;
import com.stacksimply.restservices.exceptions.UserNotFoundException;
import com.stacksimply.restservices.services.UserService;

@RestController
@RequestMapping("/versioning/mediatype/users")
public class UserMediaTypeVersioningController {
	@Autowired
	private UserService userService;
	
	@Autowired
	ModelMapper modelMapper;

	//Custom Header Based Versioning - V1
	@GetMapping(value="/{id}", produces="application/vnd.stacksimplify.app-v1+json")
	public UserDtoV1 getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {// It means value should be atleast 1 or
																			// greater
		Optional<User> userOptional =  userService.getUserById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		User user = userOptional.get();
		UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);
		return userDtoV1;

	}
	
	//Custom Header Based Versioning - V2
	@GetMapping(value="/{id}", produces="application/vnd.stacksimplify.app-v2+json")
	public UserDtoV2 getUserById2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {// It means value should be atleast 1 or
																			// greater
		Optional<User> userOptional =  userService.getUserById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		User user = userOptional.get();
		UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);
		return userDtoV2;

	}

}
