package com.coplaning.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.Flight;
import dao.Passenger;
import dao.Pilot;

@Path("/example")
public class ExampleResource {
	Flight F = new Flight();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flight")
	public List<Flight> getLF() {
		return F.listFlight();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flight")
	public Flight getF(String ID_Flight) {
		return F.getFlight(ID_Flight);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flight/{id}")
	public void addF(@PathParam("id") String id) {
		Flight G = F.getFlight(id);
		F.putFlight(G);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flight/{id}")
	public void DeleteF(@PathParam("id") String id) {
		Flight G = F.getFlight(id);
		F.deleteFlight(G);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flight/{id}")
	public void PostF(@PathParam("id") String id) {
		Flight G = F.getFlight(id);
		F.postFlight(G);
	}

	// Modify the details of the pilot for the corresponding id
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/pilot/id")
	public void ModifyPilot(Pilot p) {
		System.out.println(p.ID_pilot);
	}

	// Modify the details of flights for the corresponding id
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/flight/id")
	public void ModifyFlight(Flight f) {
		System.out.println(f.ID_flight);
	}

	// Modify the details of passenger for the corresponding id
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/passenger/id")
	public void ModifyPassenger(Passenger p) {
		System.out.println(p.ID_passenger);
	}
}
