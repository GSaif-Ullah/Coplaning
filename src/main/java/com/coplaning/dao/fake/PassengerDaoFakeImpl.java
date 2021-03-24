package com.coplaning.dao.fake;


import dao.Passenger;
import dao.PassengerContainer;
import dao.PassengerDAO;

public class PassengerDaoFakeImpl implements PassengerDAO {

	public long addPassenger(PassengerContainer passenger) {
		return -1;
	}

	public PassengerContainer getPassenger(long id) {
		PassengerContainer container = new PassengerContainer() ;
		container.getPassengers().add(new Passenger("GSaif"));
		return container;
	}


}
