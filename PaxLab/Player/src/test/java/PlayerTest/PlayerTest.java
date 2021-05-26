package PlayerTest;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import dk.group6.common.services.IGamePluginService;

//Decoration for injection of bundlecontext
import javax.inject.Inject;

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

	private IGamePluginService plugin;
	private ServiceTracker tracker;
 

	@Inject
	private BundleContext bc;


	//@Inject
	//private IGamePluginService plugins;
	

	@Configuration
	public Option[] config(){
		System.out.println ( 
		"Working Directory = " + System.getProperty("user.dir"));
		return options(
			provision(
				mavenBundle().groupId("dk.group6").artifactId("Common").version("1.0-SNAPSHOT"),
				mavenBundle().groupId("dk.group6").artifactId("Player").version("1.0-SNAPSHOT")
			), 
			junitBundles()
		);
	}

    @Before
    public void setUp() throws Exception {
		plugin = getPluginService();
    }
 
    @After
    public void tearDown() {
		tracker.close();
    }

	@Test
	public void assertTest() {
		assertNotNull(bc);
		//System.out.println("the value of plugins is " +plugins);
		//assertNotNull(plugins);
	}
 
    @Test
    public void PlayTest() throws BundleException {
		bc.getBundle().start();
    }
	
	public IGamePluginService getPluginService() throws InterruptedException {
		System.out.println("this is it " +bc);
		tracker = new ServiceTracker(bc, IGamePluginService.class, null);
		tracker.open();
		IGamePluginService services = (IGamePluginService) tracker.waitForService(1000);
		System.out.println("services tracked " + (tracker.isEmpty()? "None" : "Something"));
		System.out.println(services);
		return services;
	} 
}
