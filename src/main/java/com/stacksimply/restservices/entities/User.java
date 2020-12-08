package com.stacksimply.restservices.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Model to create a new user")//swagger related
@Entity
@Table(name = "user")
//@Table(name="user", schema = "usermanagment")
//Schema can be used for real time projects
// If we are not using @table annotation in entity class
// then name of table should be same as entity name
//@JsonIgnoreProperties({"firstname","lastname"})//Static FIltering , here we can have multiple columns and that is difference with @JsonIgnore
// commenting @JsonIgnoreProperties as its part of static filtering
//@JsonFilter(value = "userFIlter")//Commenting to practice @JsonView
public class User extends RepresentationModel<User> {
	
	@Id // Primary Key
	@GeneratedValue // Check for more Generated Strategies
	@JsonView(Views.External.class)
	@ApiModelProperty(notes = "userid - Unique identifier of user", required = true, position = 1)
	private Long userid;

	@Size(min = 2,max = 50)
	@Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
	@NotEmpty(message = "Username is Mandotory filed. Please provide the Username.")
	@JsonView(Views.External.class)
	@ApiModelProperty(notes = "username of user", required = false, position = 2)//For swagger
	private String username;

	// Length is for only String,
	// if unique then in Db also there will be index.
	// We can have multiple unique Columns
	@Size(min = 2,max = 50, message = "First shuld have atleast 2 characters.")
	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	@JsonView(Views.External.class)
	@ApiModelProperty(notes = "First name of the User.", example = "Lucky", required = false, position = 3)//Swagger
	private String firstname;

	@Column(name = "LAST_NAME", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String lastname;

	@Column(name = "EMAIL_ADDRESS", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String email;

	@Column(name = "ROLE", length = 50, nullable = false)
	@JsonView(Views.Internal.class)
	private String role;

	@Column(name = "SSN", length = 50, nullable = false, unique = true)
	// @Column(name = "SSN", length = 50, nullable = true, unique = true)
	// @JsonIgnore//Static filtering , commenting as its part of static filtering
	@JsonView(Views.Internal.class)
	@ApiModelProperty(notes = "SSN of the User.", example = "SSN1010", required = true, position = 4)
	private String ssn;

	@OneToMany(mappedBy = "user") // It means that there will be userid column in orders table an no aditional
									// column in users , here owner entity is order
	@JsonView(Views.Internal.class)
	private List<Order> order;

	@Column(name="ADDRESS")
	private String address;

	// No Argument Constructor is Must for JPA
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Below is Optional for JPA
	public User(Long userid,
			@NotEmpty(message = "Username is Mandotory filed. Please provide the Username.") String username,
			@Size(min = 2, message = "First shuld have atleast 2 characters.") String firstname, String lastname,
			String email, String role, String ssn, List<Order> order, String address) {
		super();
		this.userid = userid;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.order = order;
		this.address = address;
	}

	// Getter and Setter are Must for JPA

	public String getUsername() {
		return username;
	}

	

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// Below Is Optional
	@Override
	public String toString() {
		return "User [id=" + userid + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}

}
