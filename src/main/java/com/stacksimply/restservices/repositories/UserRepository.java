package com.stacksimply.restservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stacksimply.restservices.entities.User;


@Repository
//<User, Long>Below Long id the primary key type of User
public interface UserRepository extends JpaRepository<User, Long>{
	//Below is the advantage of JPA
	//No need to write query just findBy+FirstCpaital Letter of column name in entity i.e java field name
	//In Below we have guarantee of getting only on User because in entity for column name username we have mentioned unique constraint
	public User findByUsername(String username);

}
