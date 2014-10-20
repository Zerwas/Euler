
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class Test_Primelist extends Test_Primelist_Basic{

	protected Primelist p2, pmax;


	@Test
	public void testHalfInt() {
		p2 = new Primelist(Integer.MAX_VALUE / 2);
		List<Integer> primes=new LinkedList<>();
		primes.add(1073741309);
		primes.add(1073741311);
		primes.add(1073741329);
		primes.add(1073741371);
		primes.add(1073741381);
		primes.add(1073741387);
		primes.add(1073741399);
		primes.add(1073741419);
		primes.add(1073741441);
		primes.add(1073741467);
		primes.add(1073741477);
		primes.add(1073741503);
		primes.add(1073741527);
		primes.add(1073741561);
		primes.add(1073741567);
		primes.add(1073741621);
		primes.add(1073741651);
		primes.add(1073741663);
		primes.add(1073741671);
		primes.add(1073741689);
		primes.add(1073741717);
		primes.add(1073741719);
		primes.add(1073741723);
		primes.add(1073741741);
		primes.add(1073741783);
		primes.add(1073741789);
		Iterator<Integer> it = p2.iterator(primes.get(0)-1);
		for (Integer p : primes) {
			assertTrue("Problem with "+p,p2.isPrime(p)&&p.equals(it.next()));
		}
	}


	@Test
	public void testInt() {
		pmax=new Primelist(Integer.MAX_VALUE);
		Iterator<Integer> it = pmax.iterator(2147483647-1);
		assertTrue("2147483647 should be prime", it.next().equals((2147483647)));
		assertFalse("last prime "+it.next(),it.hasNext());
	}
}
