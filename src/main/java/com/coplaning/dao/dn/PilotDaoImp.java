package com.coplaning.dao.dn;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.coplaning.dao.DAO;
import com.coplaning.dao.Flight;
import com.coplaning.dao.FlightContainer;
import com.coplaning.dao.Passenger;
import com.coplaning.dao.PassengerContainer;
import com.coplaning.dao.Pilot;
import com.coplaning.dao.PilotContainer;
import com.coplaning.dao.PilotDAO;

public class PilotDaoImp implements PilotDAO {
	private PersistenceManagerFactory pmf;

	public  PilotDaoImp(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
		this.initiatePilots();
	}
	

	//Create a Passengers database
	@SuppressWarnings("deprecation")
	public void initiatePilots() {
		PassengerContainer container = new PassengerContainer(new Passenger("password1", "jean@gmail.com", "test", "jean",new Date(95,1,5),"phone1"));
		int id_pass = DAO.getPassengerDao().addPassengerContainer(container);
		FlightContainer F3=new FlightContainer(new Flight("Meaux", "Pontoise", "FGYPG","aircraft3",new Date(121,4,15), 7, 45,"images/aircraft3.jpg"));
	    int id_fli = DAO.getFlightDao().addFlightContainer(F3);
		PilotContainer P1=new PilotContainer(new Pilot(id_pass,"password1", "jean@gmail.com", "test", "jean",new Date(95,1,5),"phone1",id_fli));
		int id_pil = addPilotContainer(P1);
		DAO.getFlightDao().BookPilot(id_fli, id_pil);
		DAO.getPassengerDao().BookPilot(id_pass,id_pil);
		
		PassengerContainer P2=new PassengerContainer(new Passenger("password2", "g.saif-ullah@outlook.fr", "ghulam", "saif",new Date(98,7,20),"phone2"));
		id_pass = DAO.getPassengerDao().addPassengerContainer(P2);
		FlightContainer F4=new FlightContainer(new Flight("CDG", "Pontoise", "FGYPG","aircraft3",new Date(121,4,6), 7, 124,"images/aircraft3.jpg"));
	    F4.getFlight().setaPassenger(0);
		id_fli = DAO.getFlightDao().addFlightContainer(F4);
		DAO.getPassengerDao().BookFlight(0, id_fli);
		PilotContainer Pi2=new PilotContainer(new Pilot(id_pass,"password2", "g.saif-ullah@outlook.fr", "ghulam", "saif",new Date(98,7,20),"phone2",id_fli));
		id_pil = addPilotContainer(Pi2);
		DAO.getFlightDao().BookPilot(id_fli, id_pil);
		DAO.getPassengerDao().BookPilot(id_pass,id_pil);
	}
		
	//permet d'ajouter un pilote
	public int addPilotContainer(PilotContainer container) {
		PersistenceManager pm = pmf.getPersistenceManager();

		container = pm.makePersistent(container);
		int containerId = container.getId();
		pm.close();

		return containerId;
	}
	
	//recuperer la liste de tous les pilotes
	@SuppressWarnings("unchecked")
	public List<PilotContainer> getPilots() {
		List<PilotContainer> pilots = null;
		List<PilotContainer> detached = new ArrayList<PilotContainer>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(PilotContainer.class);

			pilots = (List<PilotContainer>) q.execute();
			for(int i=0; i<pilots.size(); i++) {
				pilots.get(i).getPilot().getFlights();
			}
			detached = (List<PilotContainer>) pm.detachCopyAll(pilots);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}

	//recuperer l'id d'un pilote
	public PilotContainer getPilotContainer(int id) {
		PersistenceManager pm = pmf.getPersistenceManager();

		try {
			PilotContainer container = pm.getObjectById(PilotContainer.class, id);
			PilotContainer detached = pm.detachCopy(container);

			return detached;
		} catch (JDOObjectNotFoundException e) {
			return null;
		} finally {
			pm.close();
		}

	}
	
	//recherche une liste de pilote avec un mot specifique
	@SuppressWarnings("unchecked")
	public List<PilotContainer> Search(String cas, String word) {
		List<PilotContainer> pilots = null;
		List<PilotContainer> detached = new ArrayList<PilotContainer>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(PassengerContainer.class);
			if(cas.equals("birth")) {
				q.declareParameters("java.sql.Date word");
				q.setFilter("pilot."+cas+" == word");
				
				Date date =Date.valueOf(word);
				System.out.println(date);
				pilots = (List<PilotContainer>) q.execute(date);
			}
			else if(cas.equals("id_passenger")) {
				q.declareParameters("int word");
				q.setFilter("pilot."+cas+" == word");
				int num = Integer.parseInt(word);
				pilots = (List<PilotContainer>) q.execute(num);
			}
			else {
				q.declareParameters("String word");
				q.setFilter("pilot."+cas+" == word");
				pilots = (List<PilotContainer>) q.execute(word);
			}
			for(int i=0; i<pilots.size(); i++) {
				pilots.get(i).getPilot().getFlights();
			}
			detached = (List<PilotContainer>) pm.detachCopyAll(pilots);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
				
	}

}
