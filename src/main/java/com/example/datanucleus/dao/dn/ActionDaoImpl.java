package com.example.datanucleus.dao.dn;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.example.datanucleus.dao.Action;
import com.example.datanucleus.dao.ActionContainer;
import com.example.datanucleus.dao.ActionDao;

public class ActionDaoImpl implements ActionDao {

	private PersistenceManagerFactory pmf;

	public ActionDaoImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	@SuppressWarnings("unchecked")
	public List<Action> getActions(String username) {
		List<Action> actions = null;
		List<Action> detached = new ArrayList<Action>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Action.class);
			q.declareParameters("String user");
			q.setFilter("username == user");

			actions = (List<Action>) q.execute(username);
			detached = (List<Action>) pm.detachCopyAll(actions);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}

	public void addAction(Action action) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			pm.makePersistent(action);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	public ActionContainer getActionContainer(long id) {
		PersistenceManager pm = pmf.getPersistenceManager();

		try {
			ActionContainer container = pm.getObjectById(ActionContainer.class, id);
			ActionContainer detached = pm.detachCopy(container);

			return detached;
		} catch (JDOObjectNotFoundException e) {
			return null;
		} finally {
			pm.close();
		}

	}

	public long addActionContainer(ActionContainer container) {
		PersistenceManager pm = pmf.getPersistenceManager();

		container = pm.makePersistent(container);
		long containerId = container.getId();
		pm.close();

		return containerId;
	}

}
