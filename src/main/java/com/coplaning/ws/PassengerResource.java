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
import com.coplaning.dao.Passenger;
import com.coplaning.dao.PassengerContainer;


@Path("/passenger")
public class PassengerResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public PassengerContainer getPassengerContainer(@PathParam("id") long id) {
		PassengerContainer container = DAO.getPassengerDao().getPassengerContainer(id);

		if (container == null) {
			throw new NotFoundException("Invalid container id");
		}

		return container;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public long addPassengerContainer(PassengerContainer container) {
		if (container == null) {
			throw new BadRequestException("Missing payload");
		}

		if (container.getPassenger() == null) {
			throw new BadRequestException("Missing Passengers in the container");
		}

		return DAO.getPassengerDao().addPassengerContainer(container);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void deletePassenger(@PathParam("id") String id) {
		
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void postPassenger(@PathParam("id") String id) {
	
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public List<Passenger> getPassengers() {
		List<Passenger> passengers = DAO.getPassengerDao().getPassengers();
		return passengers;
	}
}