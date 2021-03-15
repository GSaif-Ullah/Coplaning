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

import dao.Passenger;

@Path("/passenger")
public class PassengerResource {
	Passenger F=new Passenger();

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Passenger> getLF() {
		return  F.ListPassenger();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Passenger getF(String ID_Passenger) {
		return F.getPassenger(ID_Passenger);
	}
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void addF(@PathParam("id") String id) {
		Passenger G=F.getPassenger(id);
		F.PutPassenger(G);
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void DeleteF(@PathParam("id") String id) {
		Passenger G=F.getPassenger(id);
		F.DeletePassenger(G);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void PostF(@PathParam("id") String id) {
		Passenger G=F.getPassenger(id);
		F.PostPassenger(G);
	}
}
