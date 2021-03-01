package com.example.jetty_jersey.ws;

import java.sql.Date;
import java.util.List;

public class Flight {
	public String Departure;
	public String Arrival;
	public String Plane;
	public int seat;
	public int cost;
	public String ID_flight;
	public Date date;
	public List<Flight> AllFlight;
	public List<String> AllEmail;
	
	public interface FlightDAO {
		/**
		 * @return this list of Flight
		 */
		List<Flight> ListFlight();
		
		/**
		 * @param Username
		 * @param ID_Flight
		 * @return the Flight to a specific  ID_Flight
		 */		
		Flight getFlight(String ID_Flight);
		/**
		 * @param Passenger
		 * or
		 * @param Pilot
		 * Add the email of the pilot and the passengers
		 */		
		void PutEmail(String Email);
 	}
}
