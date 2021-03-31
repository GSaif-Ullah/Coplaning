package com.coplaning.dao.fake;


import java.util.List;

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

	public void addPassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		
	}

	public List<Passenger> getPassengers(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public PassengerContainer getPassengerContainer(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public long addPassengerContainer(PassengerContainer container) {
		// TODO Auto-generated method stub
		return 0;
	}


}
