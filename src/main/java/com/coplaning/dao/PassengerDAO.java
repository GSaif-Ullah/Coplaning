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
	List<PassengerContainer> getPassengers();
	
	PassengerContainer getPassengerContainer(int id);
	
	boolean CheckLogin(String username,String password);
	boolean CheckEmail(String username);

	List<PassengerContainer> Search(String cas, String word);


	int addPassengerContainer(PassengerContainer container);
	
	void deletePassengerContainer(int id);

}