package PlayerTest;

import dk.group6.common.services.IGamePluginService;
import dk.group6.player.PlayerPlugin;
import javax.inject.Inject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.*;
import static org.ops4j.pax.exam.CoreOptions.*;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Documentation for the testing framework:
 * https://ops4j1.jira.com/wiki/spaces/PAXEXAM4/pages/54263870/Documentation
 * 
 * @author peter
 */
@RunWith(PaxExam.class)
//restarts the framework for each test
@ExamReactorStrategy(PerMethod.class)
public class PlayerTest {


	@Inject
	private BundleContext bc;

	@Configuration
	public Option[] config(){
		System.out.println ( 
		"Working Directory = " + System.getProperty("user.dir"));
		return options(
		);
	}

	private IGamePluginService plugin;
 
    @Before
    public void setUp() throws Exception {
		plugin = getPluginService();
    }
 
    @After
    public void tearDown() {
       // ...
    }

	@Test
	public void assertTest() {
		//Assert.assertNotNull(bc);
	}
 
    @Test
    public void PlayTest() throws BundleException {
		bc.getBundle().start();
    }
	
	public IGamePluginService getPluginService() throws InterruptedException {
		ServiceTracker tracker = new ServiceTracker(bc, IGamePluginService.class, null);
		tracker.open();
		IGamePluginService services = (IGamePluginService) tracker.waitForService(500);
		tracker.close();
		System.out.println(services);
		return services;
	} 
}
