package datanucleus;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import org.junit.Assert;
import org.junit.Test;

import com.coplaning.dao.Passenger;
import com.coplaning.dao.PassengerContainer;

public class PassengerContainerTest {

	@Test
	public void test() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		Long containerId = null;

		// Save a container with 3 passengers
		{
			PersistenceManager pm = pmf.getPersistenceManager();

			Passenger passenger1 = new Passenger("Username 1");
			Passenger passenger2 = new Passenger("Username 2");
			Passenger passenger3 = new Passenger("Username 3");

			PassengerContainer container = new PassengerContainer();
			container.getPassengers().add(passenger1);
			container.getPassengers().add(passenger2);
			container.getPassengers().add(passenger3);

			container = pm.makePersistent(container);
			containerId = container.getId();
			pm.close();
		}

		// Retrieve this container
		{
			PersistenceManager pm = pmf.getPersistenceManager();

			PassengerContainer container = pm.getObjectById(PassengerContainer.class, containerId);
			Assert.assertEquals(3, container.getPassengers().size());

			pm.close();
		}

		pmf.close();
	}

}
