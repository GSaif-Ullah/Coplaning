package com.coplaning.dao.dn;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

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
		PilotContainer P1 = new PilotContainer(new Pilot(new PassengerContainer(new Passenger("password1", "jean@gmail.com", "test", "jean",new Date(84,3,5),"phone1"))));
		PilotContainer P2 = new PilotContainer(new Pilot(new PassengerContainer(new Passenger("password1", "pierre@gmail.com", "test", "pierre",new Date(86,8,5),"phone1"))));

		PersistenceManager pm = pmf.getPersistenceManager();
		pm.makePersistent(P1);pm.makePersistent(P2);
		pm.close();
	}
		
	public long addPilotContainer(PilotContainer container) {
		PersistenceManager pm = pmf.getPersistenceManager();

		container = pm.makePersistent(container);
		long containerId = container.getId();
		pm.close();

		return containerId;
	}
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

	public PilotContainer getPilotContainer(long id) {
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

}
