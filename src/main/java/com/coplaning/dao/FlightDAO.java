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
	List<FlightContainer> getFlights();

	FlightContainer getFlightContainer(long id);
	public List<FlightContainer> Search(String cas, String word);
	public List<FlightContainer> Search(String departure,String arrival,int seat);
	public List<FlightContainer> Search(String departure,String arrival,int seat,int cost);
	
	long addFlightContainer(FlightContainer container);
	
	void deleteFlightContainer(long id);
}
