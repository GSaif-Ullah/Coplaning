package com.coplaning.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Pilot {
	
	protected int id_passenger;
	protected String password;
	protected String email;
	protected String name;
	protected String firstname;
	protected Date birth;
	protected String phone;
	protected String permis;
	protected String id_card;
	protected List<Integer> flights;
	
	
	public Pilot() {
		super();
	}

	public Pilot(int id_passenger, String password, String email, String name, String firstname, Date birth,
			String phone) {
		super();
		this.id_passenger = id_passenger;
		this.password = password;
		this.email = email;
		this.name = name;
		this.firstname = firstname;
		this.birth = birth;
		this.phone = phone;
	}

	public Pilot(String password, String email, String name, String firstname, Date birth, String phone, String permis,
			String id_card) {
		super();
		this.password = password;
		this.email = email;
		this.name = name;
		this.firstname = firstname;
		this.birth = birth;
		this.phone = phone;
		this.permis = permis;
		this.id_card = id_card;
	}

	public Pilot(String password, String email, String name, String firstname, Date birth, String phone) {
		super();
		this.password = password;
		this.email = email;
		this.name = name;
		this.firstname = firstname;
		this.birth = birth;
		this.phone = phone;
	}
	
	

	public Pilot(int id_passenger, String password, String email, String name, String firstname, Date birth,
			String phone, int flight) {
		super();
		this.id_passenger = id_passenger;
		this.password = password;
		this.email = email;
		this.name = name;
		this.firstname = firstname;
		this.birth = birth;
		this.phone = phone;
		this.flights = new ArrayList<Integer>() ;
		this.flights.add(flight);
	}

	public int getId_passenger() {
		return id_passenger;
	}

	public void setId_passenger(int id_passenger) {
		this.id_passenger = id_passenger;
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

	public String getPermis() {
		return permis;
	}

	public void setPermis(String permis) {
		this.permis = permis;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
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
}
