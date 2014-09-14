
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class Test_Primelist extends Test_Primelist_Basic{

	protected Primelist p2, pmax;


	@Test
	public void testmax() {
		p2 = new Primelist(Integer.MAX_VALUE / 2);
		pmax = new Primelist((Integer.MAX_VALUE/4)*3);
		for (int i = 1; i < Integer.MAX_VALUE / 2; i++) {
			assertFalse("Difference at number " + i,
					p2.isPrime(i) ^ pmax.isPrime(i));
		}
	}

}
