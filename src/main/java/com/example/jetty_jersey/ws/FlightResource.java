package com.example.jetty_jersey.ws;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.Flight;

@Path("/flight")
public class FlightResource {
	Flight F = new Flight();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> getLF() {
		System.out.println(F.listFlight());
		return F.listFlight();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Flight getF(String ID_Flight) {
		return F.getFlight(ID_Flight);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void addF(@PathParam("id") String id) {
		Flight G = F.getFlight(id);
		F.putFlight(G);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void DeleteF(@PathParam("id") String id) {
		Flight G = F.getFlight(id);
		F.deleteFlight(G);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void PostF(@PathParam("id") String id) {
		Flight G = F.getFlight(id);
		F.postFlight(G);
	}
}
