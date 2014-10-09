import java.util.BitSet;
import java.util.Iterator;

/**
 * 
 * @author zerwas calculates primes
 */
public class Primelist implements Iterable<Integer> {

	private int maxnumber;
	private BitSet numbers;

	/**
	 * 
	 * @param i
	 * @return true iff i is not prime
	 */
	public boolean isNotPrime(int i) {
		return numbers.get(i-1);
	}

	/**
	 * 
	 * @param i
	 * @return true iff i is prime
	 */
	public boolean isPrime(int i) {
		return !numbers.get(i-1);
	}

	/**
	 * 
	 * @param i
	 * @return a MS that contains the primefactors of i
	 */
	public Multiset<Integer> getPrimefactors(int i) {
		int k = 2;
		Multiset<Integer> erg = new Multiset<>();
		while (i > 1) {
			if (!numbers.get(k)) {
				if (i % k == 0) {
					i /= k;
					erg.add(k);
				} else
					k++;
			} else
				k++;
		}
		return erg;
	}
/**
 * 
 * @param max array length--> max number represented is max-1
 */
	public Primelist(int max) {
		maxnumber = max;
		numbers = new BitSet(maxnumber);
		numbers.set(0);//1 is not prime
		for (int g = 3; g < maxnumber; g += 2)// erase for 2
		{
			numbers.set(g);
		}
		// TODO handle 3 separately for better performance
		int upperBound = (int) Math.sqrt(maxnumber) + 1;
		for (int i = 3; i < upperBound; i += 2) {
			if (!numbers.get(i-1)) {
				for (int j = (i * i)-1; j < maxnumber; j += i << 1)
					{
						numbers.set(j);
					}
			}
		}
	}

	@Override
	public Iterator<Integer> iterator() {
		return new PrimeIterator();
	}
	
	public Iterator<Integer> iterator(int start) {
		return new PrimeIterator(start);
	}

	/**
	 * 
	 * @author zerwas iterator that returns all the primes under maxnumber
	 */
	private class PrimeIterator implements Iterator<Integer> {

		int i = 0;

		public PrimeIterator() {
			next();
		}
		/**
		 * gives primes bigger than start
		 * @param start
		 */
		public PrimeIterator(int start) {
			i=start;
			next();
		}

		@Override
		public boolean hasNext() {
			return i != -1;
		}

		@Override
		public Integer next() {
			int x = ++i;
			while (i < maxnumber && numbers.get(i))
				i++;
			if (i == maxnumber)
				i = -1;
			return x;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();

		}
	}
}
