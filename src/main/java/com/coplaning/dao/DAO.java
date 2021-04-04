package com.coplaning.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import com.coplaning.dao.dn.FlightDaoImp;
import com.coplaning.dao.dn.PassengerDaoImp;

public class DAO {

	private static PassengerDAO passengerDao = null;
	private static FlightDAO flightDao = null;
	
	
	public static PassengerDAO getPassengerDao() {
		if (passengerDao == null) {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
			passengerDao = new PassengerDaoImp(pmf);
		}

		return passengerDao;
	}

	public static FlightDAO getFlightDao() {
		if (flightDao == null) {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
			flightDao = new FlightDaoImp(pmf);
		}

		return flightDao;
	}

}
