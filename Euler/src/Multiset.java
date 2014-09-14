import java.util.Iterator;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 
 * @author zerwas, ben (aber nur zum testen ^^)
 * 
 * @param <T>
 *            sorted MS with elements of type T
 */
public class Multiset<T extends Comparable<T>> implements
		Iterable<Entry<T, Integer>> {
	SortedMap<T, Integer> elements = new TreeMap<>();

	// TODO add mothods to remove lements
	/**
	 * 
	 * @param b
	 * @return new MS that contains the elements of the intersection of b and
	 *         this
	 */
	public Multiset<T> intersect(Multiset<T> b) {
		Multiset<T> erg = new Multiset<>();
		Iterator<Entry<T, Integer>> iter = b.iterator();
		Entry<T, Integer> ele = iter.hasNext() ? iter.next() : null;
		for (Entry<T, Integer> entry : this) {
			// skip elements in b that do not occur in this
			while (ele != null && entry.getKey().compareTo(ele.getKey()) > 0) {
				ele = iter.hasNext() ? iter.next() : null;
			}
			// if b is at the end finish
			if (ele == null)
				break;
			// if this and b contain an element add it to the result
			if (entry.getKey().equals(ele.getKey())) {
				erg.add(entry.getKey(),
						Math.min(entry.getValue(), ele.getValue()));
			}
		}
		return erg;
	}

	/**
	 * 
	 * @param b
	 * @return new MS that contains the elements of this-b
	 */
	public Multiset<T> minus(Multiset<T> b) {
		Multiset<T> erg = new Multiset<>();
		Iterator<Entry<T, Integer>> iter = b.iterator();
		Entry<T, Integer> ele = iter.hasNext() ? iter.next() : null;
		for (Entry<T, Integer> entry : this) {
			// skip elements in b that do not occur in this
			while (ele != null && entry.getKey().compareTo(ele.getKey()) > 0) {
				ele = iter.hasNext() ? iter.next() : null;

			}
			if (ele != null && entry.getKey().equals(ele.getKey())) {
				// if this contains an element more often than b
				if (entry.getValue() > ele.getValue())
					erg.add(entry.getKey(), entry.getValue() - ele.getValue());
			} else {
				// if b does not contain an element in this add it to the result
				erg.add(entry.getKey(), entry.getValue());
			}
		}
		return erg;
	}

	/**
	 * 
	 * @param b
	 * @return new MS that contains the union of this and b
	 */
	public Multiset<T> union(Multiset<T> b) {
		Multiset<T> erg = new Multiset<>();
		for (Entry<T, Integer> entry : this) {
			erg.add(entry.getKey(), entry.getValue());
		}
		for (Entry<T, Integer> entry : b) {
			erg.add(entry.getKey(), entry.getValue());
		}
		return erg;
	}

	/**
	 * 
	 * @param elem
	 *            add this element one time
	 */
	public void add(T elem) {
		add(elem, 1);
	}

	/**
	 * 
	 * @return highest number any element occurs
	 */
	public int maxOccurence() {
		int max = 0;
		for (int x : elements.values()) {
			max = max < x ? x : max;
		}
		return max;
	}

	/**
	 * 
	 * @param elem
	 *            add this element x times
	 * @param x
	 */
	public void add(T elem, Integer x) {
		Integer count = elements.get(elem);
		elements.put(elem, (count == null ? 0 : count) + x);
	}

	@Override
	public Iterator<Entry<T, Integer>> iterator() {
		return elements.entrySet().iterator();
	}

	@Override
	public String toString() {
		return elements.toString();
	}
}