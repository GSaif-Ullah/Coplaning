package dao;

import com.coplaning.dao.fake.PassengerDaoFakeImpl;

public class DAO {

	public static PassengerDAO getPassengerDao() {
		return new PassengerDaoFakeImpl();
	}

}
