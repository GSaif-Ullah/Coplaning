package com.coplaning.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.annotations.PersistenceCapable;

import com.coplaning.dao.dn.FlightDaoImp;
import com.coplaning.dao.dn.PassengerDaoImp;
import com.coplaning.dao.dn.PilotDaoImp;

public class DAO {

	private static PassengerDAO passengerDao = null;
	private static FlightDAO flightDao = null;
	private static PilotDAO pilotDao = null;
	
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
	
	public static PilotDAO getPilotDao() {
		if (pilotDao == null) {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
			pilotDao = new PilotDaoImp(pmf);
		}

		return pilotDao;
	}

}
