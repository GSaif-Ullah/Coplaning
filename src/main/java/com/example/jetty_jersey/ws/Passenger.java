package com.example.jetty_jersey.ws;

import java.util.List;

public class Passenger {
	public String Username;
	public String Password;
	public String Email;
	public String ID_passenger;
	public List<Passenger> AllPassenger;

	public interface PassengerDAO {
		/**
		 * @return this list of Passenger
		 */
		List<Passenger> ListPassenger();
		
		/**
		 * @param Username
		 * @param ID_passenger
		 * @return the passenger to a specific Username or ID_passenger
		 */
		Passenger getPassenger(String Username);
		
		Passenger getPassenger_ID(String ID_passenger);
	
		/**
		 * Add a new Passenger
		 * @param Passenger
		 */
		void PutPassenger(Passenger NewPassenger);
		/**
		 * Delete the passenger 
		 * @param Passenger
		 */
		void DeletePassenger(Passenger P);
		/**
		 * Modify a passenger
		 * @param Passenger
		 */
		void PostPassenger(Passenger P);
		}
}
