package com.coplaning.dao;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class PilotContainer{
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	protected Integer id = null;

	@Persistent(defaultFetchGroup = "true")
	protected Pilot pilot = null;

	
	public PilotContainer() {
		super();
	}


	public PilotContainer(Pilot pilot) {
		super();
		this.pilot = pilot;
	}


	public PilotContainer(Integer id, Pilot pilot) {
		super();
		this.id = id;
		this.pilot = pilot;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Pilot getPilot() {
		return pilot;
	}


	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}
	


}
