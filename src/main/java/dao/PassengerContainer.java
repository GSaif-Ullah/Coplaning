package dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class PassengerContainer {
		@PrimaryKey
		@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
		protected Long id = null;

		@Persistent
		protected List<Passenger> passengers = null;

		public PassengerContainer() {
			super();
			this.passengers = new ArrayList<Passenger>();
		}

		public Long getId() {
			return id;
		}

		public List<Passenger> getPassengers() {
			return passengers;
		}

		public void setPassengers(List<Passenger> passengers) {
			this.passengers = passengers;
		}

		public void setId(Long id) {
			this.id = id;
		}

}
