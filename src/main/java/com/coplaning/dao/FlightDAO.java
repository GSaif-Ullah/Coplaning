package com.coplaning.dao;

import java.util.List;

public interface FlightDAO {

	/**
	 * Add a flight to the database
	 * 
	 * @param action
	 */
	void addFlight(Flight flight);

	/**
	 * @param username
	 * @return the list of actions assigned to a specific user.
	 */
	List<Flight> getFlights();

	FlightContainer getFlightContainer(long id);

	long addFlightContainer(FlightContainer container);
}
