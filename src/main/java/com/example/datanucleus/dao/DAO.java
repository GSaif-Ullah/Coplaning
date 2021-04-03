package com.example.datanucleus.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import com.example.datanucleus.dao.dn.ActionDaoImpl;

public class DAO {

	private static ActionDao actionDao = null;

	public static ActionDao getActionDao() {
		if (actionDao == null) {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
			actionDao = new ActionDaoImpl(pmf);
		}

		return actionDao;
	}

}
