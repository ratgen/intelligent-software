package PlayerTest;

import javax.inject.Inject;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.*;
import static org.ops4j.pax.exam.CoreOptions.bundle;
import static org.ops4j.pax.exam.CoreOptions.junitBundles;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.options;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.container.ContainerConstants;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

/**
 * Documentation for the testing framework:
 * https://ops4j1.jira.com/wiki/spaces/PAXEXAM4/pages/54263870/Documentation
 * 
 * @author peter
 */
@RunWith(PaxExam.class)
//default reactor strategy is Per Method
public class PlayerTest {


	@Inject
	private BundleContext bc;

	@Configuration
	public Object[] config(){
		System.out.println (
		"Working Directory = " + System.getProperty("user.dir"));
		return options(
				bundle("../runner/bundles/Player_1.0.0.SNAPSHOT.jar")
		);
	}

 
    @Before
    public void setUp() {
       // ...
    }
 
    @After
    public void tearDown() {
       // ...
    }

	@Test
	public void assertTest() {
		assertNotNull(bc);
	}
 
    @Test
    public void PlayTest() throws BundleException {
		bc.getBundle().start();
    }
}
