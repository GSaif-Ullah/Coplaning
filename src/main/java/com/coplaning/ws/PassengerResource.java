package com.coplaning.ws;



import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DAO;
import dao.PassengerContainer;

@Path("/passenger")
public class PassengerResource {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PassengerContainer getPassenger(@PathParam("id") long id) {
		PassengerContainer container=DAO.getPassengerDao().getPassengerContainer(id);
		if (container == null) {
			throw new NotFoundException("Invalid container id");
		}

		return container;
	}	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public long addPassenger(PassengerContainer container) {
		if (container == null) {
			throw new BadRequestException("Missing payload");
		}

		if (container.getPassengers() == null) {
			throw new BadRequestException("Missing passenger in the container");
		}
		//DAO.getPassengerDao().addPassenger(container.getPassengers().get(0));
		return DAO.getPassengerDao().addPassengerContainer(container);
		
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void deleteF(@PathParam("id") String id) {
		
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void postF(@PathParam("id") String id) {
	
	}
}
