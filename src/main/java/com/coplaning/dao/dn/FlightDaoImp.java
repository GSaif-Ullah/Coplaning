package com.coplaning.dao.dn;

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

public class FlightDaoImp implements FlightDAO{

	private PersistenceManagerFactory pmf;

	public  FlightDaoImp(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
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