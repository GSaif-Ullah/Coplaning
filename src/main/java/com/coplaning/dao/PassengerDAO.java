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
	List<Passenger> getPassengers(String name);

	PassengerContainer getPassengerContainer(long id);

	long addPassengerContainer(PassengerContainer container);
}
