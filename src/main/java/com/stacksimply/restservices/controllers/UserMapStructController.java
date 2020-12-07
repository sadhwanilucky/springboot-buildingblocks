package com.stacksimply.restservices.controllers;

import java.util.List;
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

import com.stacksimply.restservices.dtos.UserMSDto;
import com.stacksimply.restservices.entities.User;
import com.stacksimply.restservices.exceptions.UserNotFoundException;
import com.stacksimply.restservices.mappers.UserMapper;
import com.stacksimply.restservices.repositories.UserRepository;
import com.stacksimply.restservices.services.UserService;

@RestController 
@RequestMapping(value="/mapstruct/users")
public class UserMapStructController {
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	UserMapper userMapper;
	
	
	@GetMapping
	List<UserMSDto> getAllUserMSDtos(){
		List<User> users = userRepository.findAll();
		return userMapper.usersToUserMSDtos(users);
	}
	
	@GetMapping("/{id}")
	UserMSDto getUserById(@PathVariable Long id) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not FOund");
		}
		User user = userOptional.get();
		return userMapper.userToUserMSDto(user);
	}

}
