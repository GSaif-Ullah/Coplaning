package com.coplaning.ws;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.Pilot;

@Path("/pilot")
public class PilotResource {
	Pilot F = new Pilot();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pilot> getLF() {
		return F.ListPilot();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Pilot getF(String ID_Pilot) {
		return F.getPilot(ID_Pilot);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void addF(@PathParam("id") String id) {
		Pilot G = F.getPilot(id);
		F.putPilot(G);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void deleteF(@PathParam("id") String id) {
		Pilot G = F.getPilot(id);
		F.deletePilot(G);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void postF(@PathParam("id") String id) {
		Pilot G = F.getPilot(id);
		F.postPilot(G);
	}
}
