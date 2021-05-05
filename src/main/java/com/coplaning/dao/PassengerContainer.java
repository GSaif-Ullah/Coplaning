package com.coplaning.dao;



import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class PassengerContainer {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	protected Integer id = null;

	@Persistent(defaultFetchGroup = "true")
	protected Passenger passenger = null;
	
	public PassengerContainer() {
		super();
		this.passenger = new Passenger();
	}

	 
	
	public PassengerContainer(Passenger passenger) {
		super();
		this.passenger = passenger;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	@Override
	public String toString() {
		return "id :"+String.valueOf(this.getId())+" -" + this.getPassenger()  ;
    }


}