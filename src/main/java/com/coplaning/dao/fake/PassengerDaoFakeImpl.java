package com.coplaning.dao.fake;


import java.util.List;

import com.coplaning.dao.Passenger;
import com.coplaning.dao.PassengerContainer;
import com.coplaning.dao.PassengerDAO;

public class PassengerDaoFakeImpl implements PassengerDAO {

	public long addPassenger(PassengerContainer passenger) {
		return -1;
	}

	public PassengerContainer getPassenger(long id) {
		PassengerContainer container = new PassengerContainer() ;
		container.setPassenger(new Passenger("GSaif"));
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
