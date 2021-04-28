package com.coplaning.ws;

import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.coplaning.dao.DAO;
import com.coplaning.dao.FlightContainer;

@Path("/flight")
public class FlightResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public FlightContainer getFlightContainer(@PathParam("id") long id) {
		FlightContainer container = DAO.getFlightDao().getFlightContainer(id);

		if (container == null) {
			throw new NotFoundException("Invalid container id");
		}

		return container;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public long addFlightContainer(FlightContainer container) {
		if (container == null) {
			throw new BadRequestException("Missing payload");
		}

		if (container.getFlight() == null) {
			throw new BadRequestException("Missing Flights in the container");
		}

		return DAO.getFlightDao().addFlightContainer(container);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void deleteFlight(@PathParam("id") long id) {
		DAO.getFlightDao().deleteFlightContainer(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void postFlight(@PathParam("id") String id) {
	
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public List<FlightContainer> getFlights() {
		List<FlightContainer> flights = DAO.getFlightDao().getFlights();
		return flights;
	}
	
}
