import java.lang.reflect.Field;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * 
 * @author Max Leuthäuser
 */
public class BasicTest {

	protected Multiset<Integer> a, b;

	/**
	 * Set up the Tests
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		a = new Multiset<Integer>();
		b = new Multiset<Integer>();
	}

	/**
	 * Test of toString method, of class JAttachment.
	 */
	@Test
	public void testIfStateExists() {
		assertTrue("Missing attribute in 'JIdea':", a.equals(b));
	}

	/**
	 * Return the Content of the Attribute "state"
	 */

}
