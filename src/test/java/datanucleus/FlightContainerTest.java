package datanucleus;

import java.sql.Date;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import org.junit.Assert;
import org.junit.Test;

import com.coplaning.dao.Flight;
import com.coplaning.dao.FlightContainer;

public class FlightContainerTest {

	@SuppressWarnings("deprecation")
	@Test
	public void test() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		Integer containerId = null;
		Integer containerId_2 = null;

		{
			PersistenceManager pm = pmf.getPersistenceManager();


			FlightContainer F1=new FlightContainer(new Flight("CDG", "Orly","H160","aircraft1",new Date(121,4,8), 4, 52,"images/aircraft1.jpg"));
		    FlightContainer F2=new FlightContainer(new Flight("Orly", "CDG","FGZPA","aircraft2",new Date(121,4,10), 2, 74,"images/aircraft2.jpg"));

			pm.makePersistent(F1);pm.makePersistent(F2);

			containerId = F1.getId();
			containerId_2 = F2.getId();
			pm.close();
		}

		// Retrieve this container
		{
			PersistenceManager pm = pmf.getPersistenceManager();

			//Verification depart du flight1
			FlightContainer container = pm.getObjectById(FlightContainer.class, containerId);
			Assert.assertEquals("CDG", container.getFlight().getDeparture());
			
			//Verification cost du flight 2
			container = pm.getObjectById(FlightContainer.class, containerId_2);
			Assert.assertEquals(74, container.getFlight().getCost());
			
			pm.close();
		}

		pmf.close();
	}

}
