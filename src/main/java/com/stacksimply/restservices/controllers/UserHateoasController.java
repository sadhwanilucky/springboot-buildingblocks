package com.stacksimply.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimply.restservices.entities.User;
import com.stacksimply.restservices.exceptions.UserNotFoundException;
import com.stacksimply.restservices.repositories.UserRepository;
import com.stacksimply.restservices.services.UserService;

@RestController
@RequestMapping(value="/hateoas/users")
@Validated
//Reason for creating new controller for user is just to 
//Clearly understand HATEOAS concepts otherwise there will be too much of code in usercontroller
public class UserHateoasController {
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public CollectionModel<User>  getAllUsers() {
		List<User> allUsers =  userService.getAllUsers();
		for(User user : allUsers) {
			Long userid = user.getUserid();
			Link selfLink = WebMvcLinkBuilder.linkTo(UserHateoasController.class).slash(userid).withSelfRel();
			user.add(selfLink);
			//Relation Link with getAllOrders
			
			
		}
		CollectionModel<User> resource = CollectionModel.of(allUsers);
		return resource;
	}
	
	@GetMapping("/{id}")
	//Below is for self link for Hateoas i.e information to client about the self linking
	public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) {// It means value should be atleast 1 or greater
		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();
			Long userid = user.getUserid();
			Link selfLink = WebMvcLinkBuilder.linkTo(UserHateoasController.class).slash(userid).withSelfRel();
			user.add(selfLink);
			EntityModel<User> resource = EntityModel.of(user);
		    return resource;
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());

		}

	}

}
