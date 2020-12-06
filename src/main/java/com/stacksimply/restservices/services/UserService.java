package com.stacksimply.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimply.restservices.entities.User;
import com.stacksimply.restservices.exceptions.UserExistException;
import com.stacksimply.restservices.exceptions.UserNotFoundException;
import com.stacksimply.restservices.repositories.UserRepository;

@Service // Write BusinessLogic here
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();

	}

	public User createUser(User user) throws UserExistException {
		// if user exist using username
		User existingUser = userRepository.findByUsername(user.getUsername());
		if (existingUser != null) {
			throw new UserExistException("User already exist in repository");
		}

		return userRepository.save(user);
	}

	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found in user repository");

		} else {
			return user;
		}
	}

	public User updateUserById(Long id, User user) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found in user repository, Please Provide the correct User Id");
		} else {
			user.setUserid(id); 
			return userRepository.save(user);
		}
	}

	public void deleteUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found in repository");
		}
		userRepository.deleteById(id);

	}

	public User getUserByUserName(String username) {
		return userRepository.findByUsername(username);
	}

}
