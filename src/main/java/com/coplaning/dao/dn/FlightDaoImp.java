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
		FlightContainer F1=new FlightContainer(new Flight("CDG", "Orly","H160","aircraft1",new Date(121,4,8), 4, 52,"images/aircraft1.jpg",0));
	    FlightContainer F2=new FlightContainer(new Flight("Orly", "CDG","FGZPA","aircraft2",new Date(121,4,10), 2, 74,"images/aircraft2.jpg"));
	    FlightContainer F3=new FlightContainer(new Flight("Meaux", "Pontoise", "FGYPG","aircraft3",new Date(121,4,8), 7, 45,"images/aircraft3.jpg"));
	    FlightContainer F4=new FlightContainer(new Flight("Lognes", "Etampes", "FGHLY","aircraft4",new Date(121,4,7), 2, 112,"images/aircraft4.jpg"));
	    FlightContainer F5=new FlightContainer(new Flight("Meaux", "Pontoise", "FG536","aircraft3",new Date(121,4,8), 1, 65,"images/aircraft5.jpg"));
	    FlightContainer F6=new FlightContainer(new Flight("Meaux", "Lognes", "FGCIX","aircraft1",new Date(121,4,9), 8, 57,"images/aircraft6.jpg"));
	    FlightContainer F7=new FlightContainer(new Flight("Pontoise", "Pontoise", "AB346","aircraft2",new Date(121,4,10), 7, 39,"images/aircraft7.jpg"));
	    FlightContainer F8=new FlightContainer(new Flight("Lognes", "Meaux", "AB586","aircraft1",new Date(121,4,5), 4, 107,"images/aircraft8.jpg"));
	    FlightContainer F9=new FlightContainer(new Flight("Pontoise", "Meaux", "AB516","aircraft3",new Date(121,4,14), 3, 86,"images/aircraft9.jpg"));
	    FlightContainer F10=new FlightContainer(new Flight("Etampes", "Pontoise", "HBYSH","aircraft4",new Date(121,4,7), 5, 63,"images/aircraft10.jpg"));
	    FlightContainer F11=new FlightContainer(new Flight("Etampes", "Meaux", "IDUEK","aircraft1",new Date(121,4,8), 5, 43,"images/aircraft11.jpg"));
	    FlightContainer F12=new FlightContainer(new Flight("Lognes", "Pontoise", "AB549","aircraft2",new Date(121,4,9), 5, 83,"images/aircraft12.jpg"));
	    PersistenceManager pm = pmf.getPersistenceManager();
		pm.makePersistent(F1);pm.makePersistent(F2);pm.makePersistent(F3);pm.makePersistent(F4);
		pm.makePersistent(F5);pm.makePersistent(F6);pm.makePersistent(F7);pm.makePersistent(F8);
		pm.makePersistent(F9);pm.makePersistent(F10);pm.makePersistent(F11);pm.makePersistent(F12);
		pm.close();
	}
	
	public FlightContainer getFlightContainer(int id) {
		PersistenceManager pm = pmf.getPersistenceManager();

		try {
			FlightContainer container = pm.getObjectById(FlightContainer.class, id);
			
			container.getFlight().getPassengers();
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

	public int addFlightContainer(FlightContainer container) {
		PersistenceManager pm = pmf.getPersistenceManager();

		container = pm.makePersistent(container);
		int containerId = container.getId();
		pm.close();
		
		
		return containerId;
	}
	
	public void deleteFlightContainer(int id) {
		PersistenceManager pm = pmf.getPersistenceManager();
		FlightContainer container = pm.getObjectById(FlightContainer.class, id);
		System.out.println(container);
		pm.deletePersistent(container);
		
		pm.flush();
		pm.close();
	}

	public void BookFlight(int id_flight,int id_passager) {
		PersistenceManager pm = pmf.getPersistenceManager();
		FlightContainer container = pm.getObjectById(FlightContainer.class, id_flight);

		container.getFlight().setaPassenger(id_passager);
		pm.close();
	}
	
	public void BookFlightSeat(int id_flight,int seat) {
		PersistenceManager pm = pmf.getPersistenceManager();
		FlightContainer container = pm.getObjectById(FlightContainer.class, id_flight);

		container.getFlight().DecSeat(seat);
		pm.close();
	}

	public void BookPilot(int id_flight,int id_pilot) {
		PersistenceManager pm = pmf.getPersistenceManager();
		FlightContainer container = pm.getObjectById(FlightContainer.class, id_flight);

		container.getFlight().setId_pilot(id_pilot);
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
			for(int i=0; i<flights.size(); i++) {
				flights.get(i).getFlight().getPassengers();
			}
			//System.out.println(flights.get(0).getFlight().getPassengers());
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
	
	
	//recherche avec depart
	@SuppressWarnings("unchecked")
	public List<FlightContainer> Search(String departure){
		List<FlightContainer> flights = null;
        List<FlightContainer> detached = new ArrayList<FlightContainer>();
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            Query q = pm.newQuery(FlightContainer.class);
            q.declareParameters("String departure");
            q.setFilter("flight.departure == departure ");
            
            flights = (List<FlightContainer>) q.executeWithArray((new Object[]{departure}));
            for(int i=0; i<flights.size(); i++) {
				flights.get(i).getFlight().getPassengers();
			}
            detached = (List<FlightContainer>) pm.detachCopyAll(flights);
            tx.commit();
            if (detached.size()==0) {

                return null;
            }
            else {    
                return detached;
                
            }
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	//recherche avec depart et une date
	@SuppressWarnings("unchecked")
	public List<FlightContainer> Search(String departure,String d){
		List<FlightContainer> flights = null;
        List<FlightContainer> detached = new ArrayList<FlightContainer>();
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            Query q = pm.newQuery(FlightContainer.class);
            q.declareParameters("String departure,java.sql.Date date1");
            q.setFilter("flight.departure == departure && date1==flight.date ");
            
            //transforme les string en date 
            Date date1 =Date.valueOf(d);
            
            flights = (List<FlightContainer>) q.executeWithArray((new Object[]{departure,date1}));
            for(int i=0; i<flights.size(); i++) {
				flights.get(i).getFlight().getPassengers();
			}
            detached = (List<FlightContainer>) pm.detachCopyAll(flights);
            tx.commit();
            if (detached.size()==0) {

                return null;
            }
            else {    
                return detached;
                
            }
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	//recherche avec depart et une arrivee	
	@SuppressWarnings("unchecked")
	public List<FlightContainer> SearchLieu(String departure,String arrival){
		List<FlightContainer> flights = null;
        List<FlightContainer> detached = new ArrayList<FlightContainer>();
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            Query q = pm.newQuery(FlightContainer.class);
            q.declareParameters("String departure,String arrival");
            q.setFilter("flight.departure == departure && flight.arrival == arrival ");
            
            
            flights = (List<FlightContainer>) q.executeWithArray((new Object[]{departure,arrival}));
            for(int i=0; i<flights.size(); i++) {
				flights.get(i).getFlight().getPassengers();
			}
            detached = (List<FlightContainer>) pm.detachCopyAll(flights);
            tx.commit();
            if (detached.size()==0) {

                return null;
            }
            else {    
                return detached;
                
            }
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	//recherche avec depart entre deux dates
	@SuppressWarnings("unchecked")
	public List<FlightContainer> Search(String departure,String d1,String d2){
		List<FlightContainer> flights = null;
        List<FlightContainer> detached = new ArrayList<FlightContainer>();
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            Query q = pm.newQuery(FlightContainer.class);
            q.declareParameters("String departure,java.sql.Date date1, java.sql.Date date2");
            q.setFilter("flight.departure == departure && date1<=flight.date && flight.date<=date2");
            
            //transforme les string en date 
            Date date1 =Date.valueOf(d1);
            Date date2 =Date.valueOf(d2);
            
            flights = (List<FlightContainer>) q.executeWithArray((new Object[]{departure,date1,date2}));
            for(int i=0; i<flights.size(); i++) {
				flights.get(i).getFlight().getPassengers();
			}
            detached = (List<FlightContainer>) pm.detachCopyAll(flights);
            tx.commit();
            if (detached.size()==0) {

                return null;
            }
            else {    
                return detached;
                
            }
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	//recherche avec depart et une arrivee entre deux dates
	@SuppressWarnings("unchecked")
	public List<FlightContainer> Search(String departure,String arrival,String d1,String d2){
		List<FlightContainer> flights = null;
        List<FlightContainer> detached = new ArrayList<FlightContainer>();
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            Query q = pm.newQuery(FlightContainer.class);
            q.declareParameters("String departure,String arrival,java.sql.Date date1, java.sql.Date date2");
            q.setFilter("flight.departure == departure && flight.arrival == arrival && date1<=flight.date && flight.date<=date2");
            
            //transforme les string en date 
            Date date1 =Date.valueOf(d1);
            Date date2 =Date.valueOf(d2);
            
            flights = (List<FlightContainer>) q.executeWithArray((new Object[]{departure,arrival,date1,date2}));
            for(int i=0; i<flights.size(); i++) {
				flights.get(i).getFlight().getPassengers();
			}
            detached = (List<FlightContainer>) pm.detachCopyAll(flights);
            tx.commit();
            if (detached.size()==0) {

                return null;
            }
            else {    
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
        List<FlightContainer> detached = new ArrayList<FlightContainer>();
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            Query q = pm.newQuery(FlightContainer.class);
            q.declareParameters("String departure,String arrival, int seat,int cost");
            q.setFilter("flight.departure == departure && flight.arrival==arrival && flight.seat>=seat && flight.cost<=cost");
            flights = (List<FlightContainer>) q.executeWithArray((new Object[]{departure, arrival,seat,cost}));
            for(int i=0; i<flights.size(); i++) {
				flights.get(i).getFlight().getPassengers();
			}
            detached = (List<FlightContainer>) pm.detachCopyAll(flights);
            tx.commit();
            if (detached.size()==0) {

                return null;
            }
            else {    
                return detached;
                
            }
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
	
	@SuppressWarnings("unchecked")
	public List<FlightContainer> Search(String departure, String arrival, int seat, int cost, int cost1) {
		List<FlightContainer> flights = null;
        List<FlightContainer> detached = new ArrayList<FlightContainer>();
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            Query q = pm.newQuery(FlightContainer.class);
            q.declareParameters("String departure,String arrival, int seat,int cost, int cost1");
            q.setFilter("flight.departure == departure && flight.arrival==arrival && flight.seat>=seat && cost<=flight.cost && flight.cost<=cost1");
            flights = (List<FlightContainer>) q.executeWithArray((new Object[]{departure, arrival,seat,cost,cost1}));
            for(int i=0; i<flights.size(); i++) {
				flights.get(i).getFlight().getPassengers();
			}
            detached = (List<FlightContainer>) pm.detachCopyAll(flights);
            tx.commit();
            if (detached.size()==0) {


                return null;
            }
            else {    

                return detached;
                
            }
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	@SuppressWarnings("unchecked")
	public List<FlightContainer> Search(String departure,String arrival,int seat,int cost,int cost1,String d1,String d2){
		List<FlightContainer> flights = null;
        List<FlightContainer> detached = new ArrayList<FlightContainer>();
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            Query q = pm.newQuery(FlightContainer.class);
            q.declareParameters("String departure,String arrival, int seat,int cost, int cost1,java.sql.Date date1, java.sql.Date date2");
            q.setFilter("flight.departure == departure && flight.arrival==arrival && flight.seat>=seat && cost<=flight.cost && flight.cost<=cost1 && date1<=flight.date && flight.date<=date2");
            
            //transforme les string en date 
            Date date1 =Date.valueOf(d1);
            Date date2 =Date.valueOf(d2);
            
            flights = (List<FlightContainer>) q.executeWithArray((new Object[]{departure, arrival,seat,cost,cost1,date1,date2}));
            for(int i=0; i<flights.size(); i++) {
				flights.get(i).getFlight().getPassengers();
			}
            detached = (List<FlightContainer>) pm.detachCopyAll(flights);
            tx.commit();
            if (detached.size()==0) {

                return null;
            }
            else {    
                return detached;
                
            }
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
	}

	@SuppressWarnings("unchecked")
	public List<FlightContainer> Search(String departure,String arrival,int seat,String d1,String d2){
		List<FlightContainer> flights = null;
        List<FlightContainer> detached = new ArrayList<FlightContainer>();
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            Query q = pm.newQuery(FlightContainer.class);
            q.declareParameters("String departure,String arrival, int seat,java.sql.Date date1, java.sql.Date date2");
            q.setFilter("flight.departure == departure && flight.arrival==arrival && flight.seat>=seat && date1<=flight.date && flight.date<=date2");
            
            //transforme les string en date 
            Date date1 =Date.valueOf(d1);
            Date date2 =Date.valueOf(d2);
            
            flights = (List<FlightContainer>) q.executeWithArray((new Object[]{departure, arrival,seat,date1,date2}));
            for(int i=0; i<flights.size(); i++) {
				flights.get(i).getFlight().getPassengers();
			}
            detached = (List<FlightContainer>) pm.detachCopyAll(flights);
            tx.commit();
            if (detached.size()==0) {

                return null;
            }
            else {    
                return detached;
                
            }
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
	}


	
	
	@SuppressWarnings("unchecked")
	public List<FlightContainer> Searchspecial(String cas, String word) {
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
				q.setFilter("flight."+cas+"== word");
				flights = (List<FlightContainer>) q.execute(word);
			
			}

			for(int i=0; i<flights.size(); i++) {
				flights.get(i).getFlight().getPassengers();
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
	
	// Renvoie les flight rechercher sinon null 
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
				for(int i=0; i<flights.size(); i++) {
					flights.get(i).getFlight().getPassengers();
				}
				detached = (List<FlightContainer>) pm.detachCopyAll(flights);
				tx.commit();
				if (detached.size()==0) {

					return null;
				}
				else {	

					return detached;
					
				}
			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}

	
}