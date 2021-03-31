package dao;

import java.util.List;

public interface PassengerDAO {

	/**
	 * Add a nes action to the database
	 * 
	 * @param action
	 */
	void addPassenger(Passenger passenger);

	/**
	 * @param username
	 * @return the list of actions assigned to a specific user.
	 */
	List<Passenger> getPassengers(String username);

	PassengerContainer getPassengerContainer(long id);

	long addPassengerContainer(PassengerContainer container);
}
