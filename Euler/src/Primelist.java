import java.util.Iterator;

/**
 * 
 * @author zerwas
 * calculates primes
 */
public class Primelist implements Iterable<Integer>{

	int maxnumber;
	boolean[] numbers;

	void erase(int x) {
		//TODO handle 3 separately for better performance
		for (int i = x * x; i < maxnumber && x <= Math.sqrt(maxnumber); i += x << 1)// x=x+prime*2,da
																					// x+prime
																					// gerade
																					// und
																					// schon
																					// bei
																					// erase
																					// 2
																					// abgehandelt
		{
			numbers[i] = true;
		}
	}

	/**
	 * 
	 * @param i
	 * @return true iff i is not prime
	 */
	public boolean isNotPrime(int i) {
		return numbers[i];
	}

	/**
	 * 
	 * @param i
	 * @return true iff i is prime
	 */
	public boolean isPrime(int i) {
		return !numbers[i];
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
			if (!numbers[k]) {
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

	public Primelist(int max) {
		maxnumber = max;
		numbers = new boolean[maxnumber];
		numbers[0]=true;
		numbers[1]=true;
		for (int g = 4; g < maxnumber; g += 2)// erase for 2
		{
			numbers[g] = true;
		}
		for (int i = 3; i < maxnumber; i += 2) {
			if (!numbers[i]) {
				erase(i);
			}
		}
	}

	@Override
	public Iterator<Integer> iterator() {
		return new PrimeIterator();
	}
	/**
	 * 
	 * @author zerwas
	 * iterator that returns all the primes under maxnumber
	 */
	private class PrimeIterator implements Iterator<Integer>{

		int i=0;
		public PrimeIterator(){
			next();
		}
		@Override
		public boolean hasNext() {
			return i!=-1;
		}

		@Override
		public Integer next() {
			int x=i++;
			while (i<maxnumber&&isNotPrime(i)) i++;
			if (i==maxnumber) i=-1;
			return x;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
			
		}
		
	}
}
