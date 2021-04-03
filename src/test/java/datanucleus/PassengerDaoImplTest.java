package datanucleus;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.junit.Assert;
import org.junit.Test;

import com.coplaning.dao.Passenger;
import com.coplaning.dao.PassengerDAO;
import com.coplaning.dao.DAO;
import com.coplaning.dao.dn.PassengerDaoImp;

public class PassengerDaoImplTest {

	@Test
	public void test() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PassengerDAO passengerDao = new PassengerDaoImp(pmf);

		Assert.assertEquals(0, passengerDao.getPassengers("user1").size());

		Passenger passenger = new Passenger();
		passenger.setUsername("user1");
		passenger.setPassword("Password1");
		passenger.setEmail("Email1");

		passengerDao.addPassenger(passenger);

		Assert.assertEquals(1, passengerDao.getPassengers("user1").size());

		DAO.getPassengerDao().getPassengers("user1");
	}

}
