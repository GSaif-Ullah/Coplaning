package datanucleus;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.junit.Assert;
import org.junit.Test;

import com.coplaning.dao.Passenger;
import com.coplaning.dao.PassengerContainer;
import com.coplaning.dao.PassengerDAO;
import com.coplaning.dao.dn.PassengerDaoImp;

public class PassengerDaoImplTest {

	@Test
	public void test() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PassengerDAO passengerDao = new PassengerDaoImp(pmf);

		Assert.assertEquals(5, passengerDao.getPassengers().size());

		PassengerContainer P1=new PassengerContainer();


		Passenger passenger = new Passenger();
		passenger.setName("user1");
		passenger.setPassword("Password1");
		passenger.setEmail("Email1");
		P1.setPassenger(passenger);

		passengerDao.addPassengerContainer(P1);

		//test le rajout du passengercontainer dans le PassengerDAO
		Assert.assertEquals(6, passengerDao.getPassengers().size());

		//check si le mail est bien trouve dans le PassengerDAO
		Assert.assertEquals(true,passengerDao.CheckEmail("Email1"));
	}

}
