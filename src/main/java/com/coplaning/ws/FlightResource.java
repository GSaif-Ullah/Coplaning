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
	public FlightContainer getFlightContainer(@PathParam("id") int id) {
		FlightContainer container = DAO.getFlightDao().getFlightContainer(id);

		if (container == null) {
			throw new NotFoundException("Invalid container id");
		}

		return container;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public int addFlightContainer(FlightContainer container) {
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
	public void deleteFlight(@PathParam("id") int id) {
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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{departure}/{arrival}/{seat}")
	public List<FlightContainer> Search(@PathParam("departure") String departure,@PathParam("arrival") String arrival,@PathParam("seat") int seat ) {
		List<FlightContainer> flights = DAO.getFlightDao().Search(departure,arrival,seat);
		System.out.println(flights.toString());
		return flights;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{departure}/{arrival}/{seat}/{cost}")
	public List<FlightContainer> Search(@PathParam("departure") String departure,@PathParam("arrival") String arrival,@PathParam("seat") int seat,@PathParam("cost") int cost ) {
		List<FlightContainer> flights = DAO.getFlightDao().Search(departure,arrival,seat,cost);
		System.out.println(flights.toString()+"ok");
		return flights;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{departure}/{arrival}/{seat}/{cost}/{cost1}")
	public List<FlightContainer> Search(@PathParam("departure") String departure,@PathParam("arrival") String arrival,@PathParam("seat") int seat,@PathParam("cost") int cost ,@PathParam("cost1") int cost1) {
		List<FlightContainer> flights = DAO.getFlightDao().Search(departure,arrival,seat,cost,cost1);
		return flights;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{departure}/{arrival}/{seat}/{cost}/{cost1}/{date1}/{date2}")
	public List<FlightContainer> Search(@PathParam("departure") String departure,@PathParam("arrival") String arrival,@PathParam("seat") int seat,@PathParam("cost") int cost ,@PathParam("cost1") int cost1,
			@PathParam("date1") String date1,@PathParam("date2") String date2) {
		List<FlightContainer> flights = DAO.getFlightDao().Search(departure,arrival,seat,cost,cost1,date1,date2);
		return flights;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{cas}/{word}")
	public List<FlightContainer> Search(@PathParam("cas") String cas,@PathParam("word") String word ) {
		List<FlightContainer> flights = DAO.getFlightDao().Search(cas, word);
		System.out.println(flights.toString());
		return flights;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id_flight}/{id_passenger}")
	public void BookFlight(@PathParam("id_flight") int id_flight,@PathParam("id_passenger") int id_passager) {

		DAO.getFlightDao().BookFlight(id_flight,id_passager );
	}
}
