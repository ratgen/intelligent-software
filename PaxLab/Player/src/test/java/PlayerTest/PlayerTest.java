package PlayerTest;

import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.player.Player;
import dk.group6.common.services.IEntityProcessingService;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import dk.group6.common.services.IGamePluginService;
import dk.group6.player.PlayerPlugin;

//Decoration for injection of bundlecontext
import javax.inject.Inject;

import org.ops4j.pax.exam.*;
import static org.ops4j.pax.exam.CoreOptions.*;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;
import org.ops4j.pax.exam.util.Filter;
import org.osgi.framework.Bundle;



import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Documentation for the testing framework:
// * https://ops4j1.jira.com/wiki/spaces/PAXEXAM4/pages/54263870/Documentation
 * 
 * @author peter
 */
@RunWith(PaxExam.class)
//restarts the framework for each test
@ExamReactorStrategy(PerMethod.class)
public class PlayerTest {

	@Inject
	private BundleContext context;

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
    }
 
    @After
    public void tearDown() {
    }

	@Test
	public void assertTest() {
		try {
			Bundle commonBundle = getBundle("dk.group6.Common");
			assertNotNull(commonBundle);

			Bundle playerBundle = getBundle("dk.group6.Player");
			assertNotNull(playerBundle);
		} catch (BundleException e){
			e.printStackTrace();
		}
	}

	@Test
	public void serviceReference() {
		ServiceReference serviceReference = context.getServiceReference(IGamePluginService.class.getName());
		ServiceReference serviceReferencer2 = context.getServiceReference(IEntityProcessingService.class.getName());
		//assertNotNull(serviceReference);
		System.out.println("service reference is: " + serviceReference);
		System.out.println("service reference2 is: " + serviceReferencer2);
	}

 
    private Bundle getBundle(String symbolicName) throws BundleException {
		for (Bundle b : context.getBundles()){
			System.out.println("Registered bundle: " + b.getSymbolicName());
			System.out.println("Bundle state: " + b.getState());
			System.out.println("Bundle registered services" + b.getRegisteredServices());
			System.out.println("Bundle id" + b.getBundleId());
			System.out.println("Bundle location" + b.getLocation());
			System.out.println("Bundle services in use" + b.getServicesInUse());
			System.out.println("--------");
			if (b.getSymbolicName().equals(symbolicName)){
				System.out.println("found the player bundle");
				return b;
			}
		}
		return null;
    }
}
