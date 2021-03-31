package dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import com.coplaning.dao.dn.PassengerDaoImp;

public class DAO {

	private static PassengerDAO passengerDao = null;

	public static PassengerDAO getPassengerDao() {
		if (passengerDao == null) {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
			passengerDao = new PassengerDaoImp(pmf);
		}

		return passengerDao;
	}

}
