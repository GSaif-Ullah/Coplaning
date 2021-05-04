package com.coplaning.dao;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Passenger {
	protected String password;
	protected String email;
	protected String name;
	protected String firstname;
	protected Date birth;
	protected String phone;
	protected List<Integer> flights;


	public Passenger() {
		super();
		this.flights = new ArrayList<Integer>();
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
	
	public Passenger(String password, String email, String name, String firstname, Date birth, String phone) {
		super();
		this.password = password;
		this.email = email;
		this.name = name;
		this.firstname = firstname;
		this.birth = birth;
		this.phone = phone;
	}


	public Passenger(String password, String email, String name, String firstname, Date birth, String phone,
			int flight) {
		super();
		this.password = password;
		this.email = email;
		this.name = name;
		this.firstname = firstname;
		this.birth = birth;
		this.phone = phone;
		this.flights = new ArrayList<Integer>();
		this.flights.add(flight);
	}

	
	public Passenger(String password, String email, String name, String firstname, Date birth, String phone,
			List<Integer> flights) {
		super();
		this.password = password;
		this.email = email;
		this.name = name;
		this.firstname = firstname;
		this.birth = birth;
		this.phone = phone;
		this.flights = flights;
		System.out.println(getFlights());
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

	public List<Integer> getFlights() {
		return flights;
	}

	public void setFlights(List<Integer> flights) {
		this.flights = flights;
	}

	public void setaFlight(int flight) {
		this.flights.add(flight);
	}
	
	@Override
	public String toString() {
		String s = "Name :"+this.getName()+" |Firstname : "+this.getFirstname()+" |birth : "+this.getBirth()+
				" |email : "+this.getEmail()+" |password : "+this.getPassword() +" |tel : "+this.getPhone();
	        return s  ;
	}



}
