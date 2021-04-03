package com.coplaning.dao;

import java.util.List;

public interface FlightDAO {

	List<Flight> listFlight();

	Flight getFlight(String ID_Flight);

	void putFlight(Flight F);

	void deleteFlight(Flight F);

	void postFlight(Flight F);

	void putEmail(String Email);

}
