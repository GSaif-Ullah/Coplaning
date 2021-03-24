package dao;

import java.util.List;

public interface PassengerDAO {

	
	/**
	 * Add a nes action to the database
	 * 
	 * @param action
	 */
	long addPassenger(PassengerContainer container);

	/**
	 * @param username
	 * @return the list of actions assigned to a specific user.
	 */
	PassengerContainer getPassenger(long id);

}
