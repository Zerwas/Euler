
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class Test_Primelist_Basic {
	protected Primelist pmin, p100;
	protected List<Integer> primes;

	/**
	 * Set up the Tests
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		primes = new LinkedList<>();
		primes.add(2);
		primes.add(3);
		primes.add(5);
		primes.add(7);
		primes.add(11);
		primes.add(13);
		primes.add(17);
		primes.add(19);
		primes.add(23);
		primes.add(29);
		primes.add(31);
		primes.add(37);
		primes.add(41);
		primes.add(43);
		primes.add(47);
		primes.add(53);
		primes.add(59);
		primes.add(61);
		primes.add(67);
		primes.add(71);
		primes.add(73);
		primes.add(79);
		primes.add(83);
		primes.add(89);
		primes.add(97);
	}

	@Test
	public void testMin() {
		pmin = new Primelist(1);
		assertFalse("minimale", pmin.iterator().hasNext());
	}

	@Test
	public void testPrimesunder100() {
		p100 = new Primelist(100);
		Iterator<Integer> p = primes.iterator();
		for (Integer prime : p100) {
			assertTrue("Smth wrong with number " + prime, prime == p.next());
		}
	}

}
