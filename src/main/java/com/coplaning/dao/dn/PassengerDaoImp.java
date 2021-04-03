package com.coplaning.dao.dn;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.coplaning.dao.Passenger;
import com.coplaning.dao.PassengerContainer;
import com.coplaning.dao.PassengerDAO;

public class PassengerDaoImp implements PassengerDAO{

	private PersistenceManagerFactory pmf;

	public  PassengerDaoImp(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	@SuppressWarnings("unchecked")
	public List<Passenger> getPassengers(String username) {
		List<Passenger> passengers = null;
		List<Passenger> detached = new ArrayList<Passenger>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Passenger.class);
			q.declareParameters("String user");
			q.setFilter("username == user");

			passengers = (List<Passenger>) q.execute(username);
			detached = (List<Passenger>) pm.detachCopyAll(passengers);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}

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

	public PassengerContainer getPassengerContainer(long id) {
		PersistenceManager pm = pmf.getPersistenceManager();

		try {
			PassengerContainer container = pm.getObjectById(PassengerContainer.class, id);
			PassengerContainer detached = pm.detachCopy(container);

			return detached;
		} catch (JDOObjectNotFoundException e) {
			return null;
		} finally {
			pm.close();
		}

	}

	public long addPassengerContainer(PassengerContainer container) {
		PersistenceManager pm = pmf.getPersistenceManager();

		container = pm.makePersistent(container);
		long containerId = container.getId();
		pm.close();

		return containerId;
	}


}