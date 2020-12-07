package com.stacksimply.restservices.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "orders")
public class Order extends RepresentationModel<Order>{
	@Id
	@GeneratedValue
	@JsonView(Views.Internal.class)
	private Long orderId;

	@JsonView(Views.Internal.class)
	private String orderDiscription;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore // Otherwise it will expect the user data, while inserting the order data in DB
	private User user;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderDiscription() {
		return orderDiscription;
	}

	public void setOrderDiscription(String orderDiscription) {
		this.orderDiscription = orderDiscription;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
