package com.coplaning.dao;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Passenger {
	protected String password;
	protected String email;
	protected String username;

	public Passenger() {
		super();
	}


	public Passenger(String username) {
		super();
		this.username = username;
	}


	public Passenger(String password, String email, String username) {
		super();
		this.password = password;
		this.email = email;
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}




}
