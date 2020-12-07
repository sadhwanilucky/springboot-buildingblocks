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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
//@Table(name="user", schema = "usermanagment")
//Schema can be used for real time projects
// If we are not using @table annotation in entity class
// then name of table should be same as entity name
@JsonIgnoreProperties({"firstname","lastname"})
public class User extends RepresentationModel<User> {
	@Id // Primary Key
	@GeneratedValue // Check for more Generated Strategies
	private Long userid;

	@Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
	@NotEmpty(message = "Username is Mandotory filed. Please provide the Username.")
	private String username;

	// Length is for only String,
	// if unique then in Db also there will be index.
	// We can have multiple unique Columns
	@Size(min = 2, message = "First shuld have atleast 2 characters.")
	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	private String firstname;

	@Column(name = "LAST_NAME", length = 50, nullable = false)
	private String lastname;

	@Column(name = "EMAIL_ADDRESS", length = 50, nullable = false)
	private String email;

	@Column(name = "ROLE", length = 50, nullable = false)
	private String role;

	@Column(name = "SSN", length = 50, nullable = false, unique = true)
	//@Column(name = "SSN", length = 50, nullable = true, unique = true)
	@JsonIgnore
	private String ssn;
	
	
	@OneToMany(mappedBy = "user") //It means that there will be userid column in orders table an no aditional column in users , here owner entity is order
	private List<Order> order;

	// No Argument Constructor is Must for JPA
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Below is Optional for JPA
	public User(Long userid,
			@NotEmpty(message = "Username is Mandotory filed. Please provide the Username.") String username,
			@Size(min = 2, message = "First shuld have atleast 2 characters.") String firstname, String lastname,
			String email, String role, String ssn, List<Order> order) {
		super();
		this.userid = userid;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.order = order;
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

	// Below Is Optional
	@Override
	public String toString() {
		return "User [id=" + userid + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}

}
