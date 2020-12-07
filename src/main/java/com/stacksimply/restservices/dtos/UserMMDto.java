package com.stacksimply.restservices.dtos;


public class UserMMDto {//UserModelMapperDTO to avoid to send entity in response
	
	private Long userid;
	private String username;
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
