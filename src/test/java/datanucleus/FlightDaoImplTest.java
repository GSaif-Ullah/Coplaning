package datanucleus;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.junit.Assert;
import org.junit.Test;
import com.coplaning.dao.Flight;
import com.coplaning.dao.FlightContainer;
import com.coplaning.dao.FlightDAO;
import com.coplaning.dao.dn.FlightDaoImp;

public class FlightDaoImplTest {

	@Test
	public void test() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		FlightDAO flightDao = new FlightDaoImp(pmf);

		Assert.assertEquals(12, flightDao.getFlights().size());

		FlightContainer F1=new FlightContainer();


		Flight flight = new Flight();
		flight.setDeparture("Paris");
		flight.setCost(50);
		flight.setPlane("Plane 507");
		F1.setFlight(flight);

		flightDao.addFlightContainer(F1);

		//test le rajout du flightcontainer dans le FlightDAO
		Assert.assertEquals(13, flightDao.getFlights().size());

		//check si il y a bien 1 flight au depart de Paris dans le FlightDAO
		Assert.assertEquals(1,flightDao.Search("Paris").size());
		
	}

}
