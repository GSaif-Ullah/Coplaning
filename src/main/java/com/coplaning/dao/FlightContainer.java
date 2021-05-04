package com.coplaning.dao;


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable
public class FlightContainer {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	protected Integer id = null;

	@Persistent(defaultFetchGroup = "true")
	protected Flight flight = null;

	
	public FlightContainer(Flight flight) {
		super();
		this.flight = flight;
	}

	public FlightContainer() {
		super();
		this.flight = new Flight();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	@Override
	public String toString() {
        return "id :"+String.valueOf(this.getId())+" -" + this.getFlight()  ;
    }
}
