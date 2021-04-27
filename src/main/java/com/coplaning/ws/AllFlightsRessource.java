package com.coplaning.ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.coplaning.dao.DAO;
import com.coplaning.dao.Flight;

@Path("/flights")
public class AllFlightsRessource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> getFlights() {
		List<Flight> flights = DAO.getFlightDao().getFlights();
		return flights;
		
	}
}
