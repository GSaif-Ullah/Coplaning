package com.coplaning.dao;

import java.sql.Date;
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

	FlightContainer getFlightContainer(int id);
	//r
	List<FlightContainer> Search(String cas, String word);
	List<FlightContainer> Search(String departure,int cost);
	List<FlightContainer> Search(String departure,String arrival,int seat);
	List<FlightContainer> Search(String departure,String arrival,int seat,int cost);
	List<FlightContainer> Search(String departure,String arrival,int seat,int cost,int cost1);
 	List<FlightContainer> Search(String departure,String arrival,int seat,int cost,int cost1,String d1,String d2);
  
	int addFlightContainer(FlightContainer container);
	
	void deleteFlightContainer(int id);
	
	void BookFlight(int id_flight, int id_passager);
}
