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
import com.coplaning.dao.FlightDAO;
import com.coplaning.dao.PassengerContainer;


public class FlightDaoImp implements FlightDAO{

	private PersistenceManagerFactory pmf;

	public  FlightDaoImp(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	    FlightContainer F1=new FlightContainer(new Flight("departure1", "arrival1", 1));
	    FlightContainer F2=new FlightContainer(new Flight("departure2", "arrival2", 2));
	    FlightContainer F3=new FlightContainer(new Flight("departure3", "arrival3", 5));
	    FlightContainer F4=new FlightContainer(new Flight("departure1", "arrival1", 4));
	    PersistenceManager pm = pmf.getPersistenceManager();
		pm.makePersistent(F1);pm.makePersistent(F2);pm.makePersistent(F3);pm.makePersistent(F4);
		pm.close();
	}

	
	@SuppressWarnings("unchecked")
	public List<FlightContainer> getFlights() {
		List<FlightContainer> flights = null;
		List<FlightContainer> detached = new ArrayList<FlightContainer>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(FlightContainer.class);
			/*q.declareParameters("String dep");
			q.setFilter("departure == dep");*/

			flights = (List<FlightContainer>) q.execute();
			detached = (List<FlightContainer>) pm.detachCopyAll(flights);
			//System.out.println(detached);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}
	// Renvoie le flight rechercher sinon null 
	/*public List<FlightContainer> Search(String departure,String arrival,int seat) {
		List<FlightContainer> flights = null;
		List<FlightContainer> detached = new ArrayList<FlightContainer>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(FlightContainer.class);
			q.declareParameters("String departure,String arrival,int seat");
			q.setFilter("flight.departure == departure && flight.arrival==arrival && flight.seat>=seat");
			flights = (List<FlightContainer>) q.execute(departure,arrival,seat);
			detached = (List<FlightContainer>) pm.detachCopyAll(flights);
			tx.commit();
			if (detached.size()==0) {
				System.out.println("ZERO");

				return null;
			}
			else {	
				System.out.println("OK");
				return detached;
				
			}
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}*/
	public FlightContainer Search(String departure,String arrival,int seat) {
			List<FlightContainer> flights = null;
			List<FlightContainer> detached = new ArrayList<FlightContainer>();
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx.begin();
				Query q = pm.newQuery(FlightContainer.class);
				q.declareParameters("String departure,String arrival,int seat");
				q.setFilter("flight.departure == departure && flight.arrival==arrival && flight.seat>=seat");
				flights = (List<FlightContainer>) q.execute(departure,arrival,seat);
				detached = (List<FlightContainer>) pm.detachCopyAll(flights);
				tx.commit();
				if (detached.size()==0) {
					System.out.println("ZERO");

					return null;
				}
				else {	
					System.out.println("OK");
					return detached.get(0);
					
				}
			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
	}
	//pas besoin a supprimer
	public void addFlight(Flight flight) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			pm.makePersistent(flight);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	public FlightContainer getFlightContainer(long id) {
		PersistenceManager pm = pmf.getPersistenceManager();

		try {
			FlightContainer container = pm.getObjectById(FlightContainer.class, id);
			FlightContainer detached = pm.detachCopy(container);
			/*int test = 
			for(int i=0;i<container.getFlights().size();i++){
			    System.out.println(container.getFlights().get(i));
			}*/
			return detached;
		} catch (JDOObjectNotFoundException e) {
			return null;
		} finally {
			pm.close();
		}

	}

	public long addFlightContainer(FlightContainer container) {
		PersistenceManager pm = pmf.getPersistenceManager();

		container = pm.makePersistent(container);
		long containerId = container.getId();
		pm.close();
		
		
		return containerId;
	}


	public void deleteFlightContainer(long id) {
		PersistenceManager pm = pmf.getPersistenceManager();
		FlightContainer container = pm.getObjectById(FlightContainer.class, id);
		System.out.println(container);
		pm.deletePersistent(container);
		
		pm.flush();
		pm.close();
	}
}