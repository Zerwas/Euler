import java.util.Iterator;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public class Multiset<T extends Comparable<T>> implements
		Iterable<Entry<T, Integer>> {
	SortedMap<T, Integer> elements = new TreeMap<>();

	public Multiset<T> intersect(Multiset<T> b) {
		Multiset<T> erg = new Multiset<>();
		Iterator<Entry<T, Integer>> iter = b.iterator();
		Entry<T, Integer> ele = iter.hasNext() ? iter.next() : null;
		for (Entry<T, Integer> entry : this) {
			while (ele != null && entry.getKey().compareTo(ele.getKey()) > 0) {
				ele = iter.hasNext() ? iter.next() : null;
			}
			if (ele == null)
				break;
			if (entry.getKey().equals(ele.getKey())) {
				erg.add(entry.getKey(),
						Math.min(entry.getValue(), ele.getValue()));
			}
		}
		return erg;
	}

	public Multiset<T> minus(Multiset<T> b) {
		Multiset<T> erg = new Multiset<>();
		Iterator<Entry<T, Integer>> iter = b.iterator();
		Entry<T, Integer> ele = iter.hasNext() ? iter.next() : null;
		for (Entry<T, Integer> entry : this) {
			//System.out.println("start" + entry);
			while (ele != null && entry.getKey().compareTo(ele.getKey()) > 0) {
				ele = iter.hasNext() ? iter.next() : null;

			}
			//System.out.println(ele);
			if (ele != null && entry.getKey().equals(ele.getKey())) {
				if (entry.getValue() > ele.getValue())
					erg.add(entry.getKey(), entry.getValue() - ele.getValue());
			} else {
				erg.add(entry.getKey(), entry.getValue());
			}
		}
		return erg;
	}

	public Multiset<T> union(Multiset<T> b){
		Multiset<T> erg=new Multiset<>();
		for (Entry<T, Integer> entry : this) {
			erg.add(entry.getKey(), entry.getValue());
		}
		for (Entry<T, Integer> entry : b) {
			erg.add(entry.getKey(), entry.getValue());
		}
		return erg;
	}
	public void add(T elem) {
		add(elem, 1);
	}
/**
 * 
 * @return highest number any element occurs
 */
	public int maxOccurence(){
		int max=0;
		for (int x : elements.values()) {
			max=max<x?x:max;
		}
		return max;
	}
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