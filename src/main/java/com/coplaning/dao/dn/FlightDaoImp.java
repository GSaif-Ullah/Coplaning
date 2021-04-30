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


public class FlightDaoImp implements FlightDAO{

	private PersistenceManagerFactory pmf;

	public  FlightDaoImp(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	    this.initiateFlights();
	}

	//Create a Flight database
	@SuppressWarnings("deprecation")
	public void initiateFlights() {
		FlightContainer F1=new FlightContainer(new Flight("departure1", "arrival1","BG587","aircraft1",new Date(121,4,8), 4, 52));
	    FlightContainer F2=new FlightContainer(new Flight("departure2", "arrival2","AF147","aircraft2",new Date(121,4,10), 2, 74));
	    FlightContainer F3=new FlightContainer(new Flight("departure3", "arrival3", "TF547","aircraft3",new Date(121,4,8), 7, 45));
	    FlightContainer F4=new FlightContainer(new Flight("departure1", "arrival1", "AB123","aircraft4",new Date(121,4,7), 2, 112));
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
		/*for (int i=0 ; i<detached.size() ;i++) {
			System.out.println(detached.get(i).getFlight().getDate());
		}*/
		return detached;
	}
	
	@SuppressWarnings("unchecked")
	public List<FlightContainer> Search(String cas, String word) {
		List<FlightContainer> flights = null;
		List<FlightContainer> detached = new ArrayList<FlightContainer>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(FlightContainer.class);
			
			if(cas.equals("seat") || cas.equals("cost")) {
				q.declareParameters("int word");
				q.setFilter("flight."+cas+" == word");
				int num = Integer.parseInt(word);
				flights = (List<FlightContainer>) q.execute(num);
			}
			else if(cas.equals("date")) {
				q.declareParameters("java.sql.Date word");
				q.setFilter("flight."+cas+" == word");
				
				Date date =Date.valueOf(word);
				System.out.println(date);
				flights = (List<FlightContainer>) q.execute(date);
			}
			else {
				q.declareParameters("String word");
				q.setFilter("flight."+cas+" == word");
				flights = (List<FlightContainer>) q.execute(word);
			
			}

			detached = (List<FlightContainer>) pm.detachCopyAll(flights);
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
	@SuppressWarnings("unchecked")
	public List<FlightContainer> Search(String departure,String arrival,int seat) {
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
	}
	
	//ne fonctionne pas
	@SuppressWarnings("unchecked")
	public List<FlightContainer> Search(String departure,String arrival,int seat,int cost) {
		List<FlightContainer> flights = null;
		List<FlightContainer> flights_1 = null;
		List<FlightContainer> detached = new ArrayList<FlightContainer>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(FlightContainer.class);
			q.declareParameters("String departure");
			q.setFilter("flight.departure == departure");
			flights = (List<FlightContainer>) q.execute(departure);
			
			q.declareParameters("String arrival");
			q.setFilter("flight.arrival == arrival");
			flights_1= (List<FlightContainer>) q.execute(arrival);
			detached = (List<FlightContainer>) pm.detachCopyAll(flights_1);
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
	}
	/*public FlightContainer Search(String departure,String arrival,int seat) {
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
	}*/
	
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