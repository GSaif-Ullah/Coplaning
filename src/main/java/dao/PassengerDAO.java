package dao;

import java.util.List;

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
	void PutPassenger(Passenger P);
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
