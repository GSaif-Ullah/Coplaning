package com.coplaning.dao;

import java.util.List;

public interface PassengerDAO {

	/**
	 * Add a passenger to the database
	 * 
	 * @param action
	 */
	void addPassenger(Passenger passenger);

	/**
	 * @param username
	 * @return the list of actions assigned to a specific user.
	 */
	List<PassengerContainer> getPassengers(String name);
	List<PassengerContainer> getPassengers();
	boolean CheckLogin(String username,String password);
	boolean CheckEmail(String username);

	PassengerContainer getPassengerContainer(long id);

	long addPassengerContainer(PassengerContainer container);
	
	void deletePassengerContainer(long id);

}