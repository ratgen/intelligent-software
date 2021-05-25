package PlayerTest;

import org.junit.runner.RunWith;
import static org.ops4j.pax.exam.CoreOptions.junitBundles;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.*;
import org.ops4j.pax.exam.junit.PaxExam;
import org.osgi.framework.BundleContext;
import org.ops4j.pax.exam.Inject;


import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;


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

	public Option[] config() {
		return options(
				junitBundles(),
				felix(),
				mavenBundle()
		);
	}
}
