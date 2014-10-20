import java.util.Arrays;

public class usefullFunctions {
	/**
	 * 
	 * @param x
	 * @return calculates x!
	 */
	static long fack(int x) {
		long erg = 1;
		for (int i = 2; i <= x; i++) {
			erg *= i;
		}
		return erg;
	}

	/**
	 * 
	 * @param n
	 * @param k
	 * @return calculates n choose k
	 */
	static long choose(int n, int k) {
		// secures that k<=n-k
		if (k > n || n < 0 || k < 0)
			return 0;
		if (k > n >> 1)
			k = n - k;
		int[] upper = new int[k];
		for (int i = n; i > n - k; i--) {
			upper[n - i] = i;
		}
		//TODO fix
		for (int i = k; i >1; i--) {
			for (int j = 0; true; j++) {
				if (upper[j] % i == 0) {//devide by gcd?
					upper[j] /= i;
					break;
				}
			}
		}
		long erg = 1;
		for (int i = 0; i < upper.length; i++) {
			erg *= upper[i];
		}
		return erg;
	}
	static <A> void t(A g){
		
	}
}
