package com.coplaning.dao.dn;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.coplaning.dao.Flight;
import com.coplaning.dao.FlightContainer;
import com.coplaning.dao.Passenger;
import com.coplaning.dao.PassengerContainer;
import com.coplaning.dao.PassengerDAO;

public class PassengerDaoImp implements PassengerDAO{

	private PersistenceManagerFactory pmf;

	public  PassengerDaoImp(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
		this.initiatePassengers();
	}
	
	//Create a Passengers database
	@SuppressWarnings("deprecation")
	public void initiatePassengers() {
		PassengerContainer P1=new PassengerContainer(new Passenger("password1", "maaz93200@gmail.com", "kathawala", "maaz",new Date(98,1,5),"phone1"));
		PassengerContainer P3=new PassengerContainer(new Passenger("password3", "fabrice@yahoo.fr", "guignard", "fabrice",new Date(98,2,2),"phone3"));
		PassengerContainer P4=new PassengerContainer(new Passenger("password4", "kevin@gmail.com", "phanvilay", "kevin",new Date(98,11,12),"phone4"));
		PassengerContainer P5=new PassengerContainer(new Passenger("password5", "mounir@live.fr", "rezig", "mounir",new Date(98,2,18),"phone5"));
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.makePersistent(P1);pm.makePersistent(P3);pm.makePersistent(P4);pm.makePersistent(P5);
		pm.close();
	}

	// pas besoin a supprimer
	public void addPassenger(Passenger passenger) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			pm.makePersistent(passenger);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	@SuppressWarnings("unchecked")
	public List<PassengerContainer> getPassengers() {
		List<PassengerContainer> passengers = null;
		List<PassengerContainer> detached = new ArrayList<PassengerContainer>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(PassengerContainer.class);
			/*q.declareParameters("String dep");
			q.setFilter("departure == dep");*/

			passengers = (List<PassengerContainer>) q.execute();
			for(int i=0; i<passengers.size(); i++) {
				passengers.get(i).getPassenger().getFlights();
			}
			detached = (List<PassengerContainer>) pm.detachCopyAll(passengers);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}
	public PassengerContainer getPassengerContainer(int id) {
		PersistenceManager pm = pmf.getPersistenceManager();

		try {
			PassengerContainer container = pm.getObjectById(PassengerContainer.class, id);
			container.getPassenger().getFlights();
			PassengerContainer detached = pm.detachCopy(container);

			return detached;
		} catch (JDOObjectNotFoundException e) {
			return null;
		} finally {
			pm.close();
		}

	}
	public PassengerContainer getPassengerID(String email) {
		PersistenceManager pm = pmf.getPersistenceManager();

		try {
			PassengerContainer container = pm.getObjectById(PassengerContainer.class, email);
			container.getPassenger().getFlights();
			PassengerContainer detached = pm.detachCopy(container);
			System.out.println("DETACHED"+detached);
			return detached;
		} catch (JDOObjectNotFoundException e) {
			return null;
		} finally {
			pm.close();
		}
		
	}


	public int addPassengerContainer(PassengerContainer container) {
		PersistenceManager pm = pmf.getPersistenceManager();

		container = pm.makePersistent(container);
		int containerId = container.getId();
		pm.close();

		return containerId;
	}
	
	public void BookPilot(int id_passenger,int id_pilot) {
		PersistenceManager pm = pmf.getPersistenceManager();
		PassengerContainer container = pm.getObjectById(PassengerContainer.class, id_passenger);

		container.getPassenger().setId_pilot(id_pilot);
		pm.close();
	}
	
	// Renvoie true si le username et le password sont dans la base de donnee
	@SuppressWarnings("unchecked")
	public boolean CheckLogin(String username, String passwrd) {
		List<PassengerContainer> passengers = null;
		List<PassengerContainer> detached = new ArrayList<PassengerContainer>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(PassengerContainer.class);
			q.declareParameters("String username,String passwrd");
			q.setFilter("passenger.email == username && passenger.password==passwrd");
			passengers = (List<PassengerContainer>) q.execute(username, passwrd);
			for(int i=0; i<passengers.size(); i++) {
				passengers.get(i).getPassenger().getFlights();
			}
			detached = (List<PassengerContainer>) pm.detachCopyAll(passengers);
			tx.commit();
			if (detached.size()==0) {
				return false;
			}
			else {						
				return true;
			}
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	// Renvoie true si le username  est dans la base de donnee
		@SuppressWarnings("unchecked")
		public boolean CheckEmail(String username) {
			List<PassengerContainer> passengers = null;
			List<PassengerContainer> detached = new ArrayList<PassengerContainer>();
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx.begin();
				Query q = pm.newQuery(PassengerContainer.class);
				q.declareParameters("String username");
				q.setFilter("passenger.email == username");
				passengers = (List<PassengerContainer>) q.execute(username);
				for(int i=0; i<passengers.size(); i++) {
					passengers.get(i).getPassenger().getFlights();
				}
				detached = (List<PassengerContainer>) pm.detachCopyAll(passengers);
				tx.commit();
				if (detached.size()==0) {
					return false;
				}
				else {						
					return true;
				}
			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}
	
	@SuppressWarnings("unchecked")
	public List<PassengerContainer> Search(String cas, String word) {
		List<PassengerContainer> passengers = null;
		List<PassengerContainer> detached = new ArrayList<PassengerContainer>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(PassengerContainer.class);
			if(cas.equals("birth")) {
				q.declareParameters("java.sql.Date word");
				q.setFilter("passenger."+cas+" == word");
				
				Date date =Date.valueOf(word);
				System.out.println(date);
				passengers = (List<PassengerContainer>) q.execute(date);
			}
			else {
				q.declareParameters("String word");
				q.setFilter("passenger."+cas+" == word");
				passengers = (List<PassengerContainer>) q.execute(word);
			}
			
			for(int i=0; i<passengers.size(); i++) {
				passengers.get(i).getPassenger().getFlights();
			}
			detached = (List<PassengerContainer>) pm.detachCopyAll(passengers);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
				
	}

	public void deletePassengerContainer(int id) {
		PersistenceManager pm = pmf.getPersistenceManager();
		PassengerContainer container = pm.getObjectById(PassengerContainer.class, id);
		System.out.println(container);
		pm.deletePersistent(container);
		
		pm.flush();
		pm.close();
	}

	public void BookFlight(int id_passenger, int id_flight) {
		PersistenceManager pm = pmf.getPersistenceManager();
		PassengerContainer container = pm.getObjectById(PassengerContainer.class, id_passenger);
		container.getPassenger().setaFlight(id_flight);
		pm.close();
	}
	
	public void setPilot(int id_passenger, int id_pilot) {
		PersistenceManager pm = pmf.getPersistenceManager();

		PassengerContainer container = pm.getObjectById(PassengerContainer.class, id_passenger);
		container.getPassenger().setId_pilot(id_pilot);

		pm.close();
	}
}