package com.coplaning.dao;

import java.sql.Date;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Flight {
	protected String departure;
	protected String arrival;
	protected String id_flight;
	protected String plane;
	protected Date date;
	protected int seat;
	protected int cost;

	public Flight() {
		super();
	}

	public Flight(String departure, String arrival, int seat) {
		super();
		this.departure = departure;
		this.arrival = arrival;
		this.seat = seat;
	}

	public Flight(String id_flight) {
		super();
		this.id_flight = id_flight;
	}

	public Flight(String departure, String arrival, String id_flight) {
		super();
		this.departure = departure;
		this.arrival = arrival;
		this.id_flight = id_flight;
	}

	public Flight(String departure, String arrival, String id_flight, String plane, Date date, int seat, int cost) {
		super();
		this.departure = departure;
		this.arrival = arrival;
		this.id_flight = id_flight;
		this.plane = plane;
		this.date = date;
		this.seat = seat;
		this.cost = cost;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getId_flight() {
		return id_flight;
	}

	public void setId_flight(String id_flight) {
		this.id_flight = id_flight;
	}

	public String getPlane() {
		return plane;
	}

	public void setPlane(String plane) {
		this.plane = plane;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		String s = "Flight id :"+this.getId_flight()+" |departure : "+this.getDeparture()+" |arrival : "+this.getArrival()+
				" |date : "+this.getDate()+" |seat available : "+String.valueOf(this.getSeat())+" |price : "+String.valueOf(this.getCost());
	        return s  ;
	}

}
