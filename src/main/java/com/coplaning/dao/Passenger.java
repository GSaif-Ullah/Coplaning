package com.coplaning.dao;

import java.sql.Date;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Passenger {
	protected String password;
	protected String email;
	protected String name;
	protected String firstname;
	protected Date birth;
	protected String phone;


	public Passenger(String password, String email, String name, String firstname, Date birth, String phone) {
		super();
		this.password = password;
		this.email = email;
		this.name = name;
		this.firstname = firstname;
		this.birth = birth;
		this.phone = phone;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public Date getBirth() {
		return birth;
	}


	public void setBirth(Date birth) {
		this.birth = birth;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Passenger() {
		super();
	}


	public Passenger(String name) {
		super();
		this.name = name;
	}


	public Passenger(String password, String email, String name) {
		super();
		this.password = password;
		this.email = email;
		this.name = name;
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
		return name;
	}
	public void setUsername(String name) {
		this.name = name;
	}




}
