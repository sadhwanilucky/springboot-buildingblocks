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

import com.stacksimply.restservices.dtos.UserMMDto;
import com.stacksimply.restservices.entities.User;
import com.stacksimply.restservices.exceptions.UserNotFoundException;
import com.stacksimply.restservices.services.UserService;

@RestController
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {
	@Autowired
	private UserService userService;
	
	@Autowired
	ModelMapper modelMapper;

	@GetMapping("/{id}")
	public UserMMDto getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {// It means value should be atleast 1 or
																			// greater
		Optional<User> userOptional =  userService.getUserById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		User user = userOptional.get();
		UserMMDto userMmDto = modelMapper.map(user, UserMMDto.class);
		return userMmDto;

	}

}
