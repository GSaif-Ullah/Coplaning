package com.coplaning.dao;

import java.util.ArrayList;
import java.util.List;

public class Pilot {
	protected PassengerContainer user;
	protected String permis;
	protected String id_card;
	protected List<FlightContainer> flights;
	
	
	
	
	public Pilot() {
		super();
	}

	public Pilot(PassengerContainer user) {
		super();
		this.user = user;
	}


	public Pilot(PassengerContainer user, String permis, String id_card) {
		super();
		this.user = user;
		this.permis = permis;
		this.id_card = id_card;
		this.flights = new ArrayList<FlightContainer>();
	}

	public Pilot(PassengerContainer user, String permis, String id_card,FlightContainer flight) {
		super();
		this.user = user;
		this.permis = permis;
		this.id_card = id_card;
		this.flights = new ArrayList<FlightContainer>();
		this.flights.add(flight);
	}

	public Pilot(PassengerContainer user, String permis, String id_card, List<FlightContainer> flights) {
		super();
		this.user = user;
		this.permis = permis;
		this.id_card = id_card;
		this.flights = flights;
	}

	public PassengerContainer getUser() {
		return user;
	}

	public void setUser(PassengerContainer user) {
		this.user = user;
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

	public List<FlightContainer> getFlights() {
		return flights;
	}

	public void setFlights(List<FlightContainer> flights) {
		this.flights = flights;
	}
	
	public void setaFlight(FlightContainer flight) {
		this.flights.add(flight);
	}
}
