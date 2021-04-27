package com.coplaning.dao.fake;


import java.util.List;

import com.coplaning.dao.Flight;
import com.coplaning.dao.FlightContainer;
import com.coplaning.dao.FlightDAO;

public class FlightDaoFakeImpl implements FlightDAO {

	public long addFlight(FlightContainer flight) {
		return -1;
	}

	public FlightContainer getFlight(long id) {
		FlightContainer container = new FlightContainer() ;
		container.setFlight(new Flight("z"));
		return container;
	}

	public void addFlight(Flight flight) {
		// TODO Auto-generated method stub
		
	}

	public List<Flight> getFlights() {
		// TODO Auto-generated method stub
		return null;
	}

	public FlightContainer getFlightContainer(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public long addFlightContainer(FlightContainer container) {
		// TODO Auto-generated method stub
		return 0;
	}


}
