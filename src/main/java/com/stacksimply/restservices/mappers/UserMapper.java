package com.stacksimply.restservices.mappers;

import java.util.List;

import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.stacksimply.restservices.dtos.UserMSDto;
import com.stacksimply.restservices.entities.User;

@Mapper(componentModel = "Spring") // For Dependency Injection
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	// User to DTO
	@Mappings({ @Mapping(source = "email", target = "emailaddress"), @Mapping(source = "role", target = "rolename") })
	// @Mapping(source = "email", target = "emailaddress")
	UserMSDto userToUserMSDto(User user);

	// List<User> to List<UserMSDto>
	List<UserMSDto> usersToUserMSDtos(List<User> users);

}
