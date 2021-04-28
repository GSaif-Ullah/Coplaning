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
import com.coplaning.dao.PassengerDAO;

public class PassengerDaoImp implements PassengerDAO{

	private PersistenceManagerFactory pmf;

	@SuppressWarnings("deprecation")
	public  PassengerDaoImp(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
		PassengerContainer P1=new PassengerContainer(new Passenger("password1", "email1", "name1", "firstname1",new Date(98,7,20),"phone1"));
		PassengerContainer P2=new PassengerContainer(new Passenger("password2", "email2", "name2", "firstname2",new Date(98,0,1),"phone3"));
		@Deprecated
		PassengerContainer P3=new PassengerContainer(new Passenger("password3", "email3", "name3", "firstname3",new Date(98,2,2),"phone4"));
		@Deprecated
		PassengerContainer P4=new PassengerContainer(new Passenger("password4", "email4", "name4", "firstname4",new Date(98,3,30),"phone5"));
		@Deprecated
		PassengerContainer P5=new PassengerContainer(new Passenger("password5", "email5", "name5", "firstname5",new Date(98,4,5),"phone5"));
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.makePersistent(P1);pm.makePersistent(P2);pm.makePersistent(P3);pm.makePersistent(P4);pm.makePersistent(P5);
		pm.close();
	}

	@SuppressWarnings("unchecked")
	public List<Passenger> getPassengers(String name) {
		List<Passenger> passengers = null;
		List<Passenger> detached = new ArrayList<Passenger>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Passenger.class);
			q.declareParameters("String user");
			q.setFilter("name == user");

			passengers = (List<Passenger>) q.execute(name);
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
	@SuppressWarnings("unchecked")
	public List<Passenger> getPassengers() {
		List<Passenger> passengers = null;
		List<Passenger> detached = new ArrayList<Passenger>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Passenger.class);
			/*q.declareParameters("String dep");
			q.setFilter("departure == dep");*/

			passengers = (List<Passenger>) q.execute();
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

	public boolean CheckLogin(String username, String password) {
		List<Passenger> passengers = null;
		List<Passenger> detached = new ArrayList<Passenger>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Passenger.class);
			q.declareParameters("String username");
			q.setFilter("email == username");
			passengers = (List<Passenger>) q.execute(username);
			detached = (List<Passenger>) pm.detachCopyAll(passengers);
			tx.commit();
			System.out.println();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return false;
	}


}