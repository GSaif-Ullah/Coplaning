package datanucleus;

import java.sql.Date;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import org.junit.Assert;
import org.junit.Test;

import com.coplaning.dao.Passenger;
import com.coplaning.dao.PassengerContainer;

public class PassengerContainerTest {

	@SuppressWarnings("deprecation")
	@Test
	public void test() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		Integer containerId = null;
		Integer containerId_2 = null;

		{
			PersistenceManager pm = pmf.getPersistenceManager();


			PassengerContainer P1=new PassengerContainer(new Passenger("password1", "maaz@gmail.com", "kathawala", "maaz",new Date(98,1,5),"phone1"));
			PassengerContainer P2=new PassengerContainer(new Passenger("test", "maaz@outlook.com", "kathawala", "maaz",new Date(98,1,5),"phone1"));

			pm.makePersistent(P1);pm.makePersistent(P2);

			containerId = P1.getId();
			containerId_2 = P2.getId();
			pm.close();
		}

		// Retrieve this container
		{
			PersistenceManager pm = pmf.getPersistenceManager();

			//Verification nom du passager1
			PassengerContainer container = pm.getObjectById(PassengerContainer.class, containerId);
			Assert.assertEquals("kathawala", container.getPassenger().getName());
			
			//Verification password du passager2
			container = pm.getObjectById(PassengerContainer.class, containerId_2);
			Assert.assertEquals("test", container.getPassenger().getPassword());
			
			pm.close();
		}

		pmf.close();
	}

}
