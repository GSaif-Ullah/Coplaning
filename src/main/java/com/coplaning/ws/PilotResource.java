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
import com.coplaning.dao.PassengerContainer;
import com.coplaning.dao.Pilot;
import com.coplaning.dao.PilotContainer;

@Path("/pilot")
public class PilotResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public PilotContainer getPassengerContainer(@PathParam("id") long id) {
		PilotContainer container = DAO.getPilotDao().getPilotContainer(id);
		if (container == null) {
			throw new NotFoundException("Invalid container id");
		}

		return container;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public long addPassengerContainer(PilotContainer container) {
		if (container == null) {
			throw new BadRequestException("Missing payload");
		}

		if (container.getPilot() == null) {
			throw new BadRequestException("Missing Passengers in the container");
		}

		return DAO.getPilotDao().addPilotContainer(container);
	}
}
