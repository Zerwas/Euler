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
		return numbers.get(i - 1);
	}

	/**
	 * 
	 * @param i
	 * @return true iff i is prime
	 */
	public boolean isPrime(int i) {
		return !numbers.get(i - 1);
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
	 * @param max
	 *            primenumbers from on to max including max
	 */
	public Primelist(int max) {
		maxnumber = max;
		numbers = new BitSet(maxnumber);
		numbers.set(0);// 1 is not prime
		for (int g = 3; g < maxnumber; g += 2)// erase for 2
		{
			numbers.set(g);
		}
		for (int g = 8; g < maxnumber && g > 0; g += 6)// erase for 3
		{
			numbers.set(g);
		}
		int upperBound = (int) Math.sqrt(maxnumber) + 1, j, x, y;
		for (int i = 5; i < upperBound; i += 2) {
			if (!numbers.get(i - 1)) {
				j = i * i - 1;
				y = i << 1;
				x = i * 6;
				numbers.set(j);
				j += y;
				// if j is divisible by 3
				if ((i + 2) % 3 == 0)
					j += y;
				else {
					numbers.set(j);
					j += i << 2;
				}
				// now j is not divisible by 2 or 3
				for (; j < maxnumber && j + y > 0; j += x) {
					numbers.set(j);
					numbers.set(j + y);
				}
				if (j + y < 0 && j > 0)
					numbers.set(j);
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
		 * 
		 * @param start
		 */
		public PrimeIterator(int start) {
			i = start - 1;
			next();
		}

		@Override
		public boolean hasNext() {
			return i != -1;
		}

		@Override
		public Integer next() {
			int x = ++i;
			try {
				i = numbers.nextClearBit(i);
			} catch (Exception e) {
				i = -1;
			}
			if (i >= maxnumber || x == 0)
				i = -1;
			return x;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();

		}
	}
}
