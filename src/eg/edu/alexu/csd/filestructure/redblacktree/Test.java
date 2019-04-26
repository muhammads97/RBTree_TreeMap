package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.junit.Assert;

public abstract class Test {

	public static void main(String[] args) {
		RBTree<Integer, Integer> rb = new RBTree<>();

		rb.insert(10, 0);
		rb.insert(15, 0);
		rb.insert(20, 0);
		rb.insert(12, 0);
		rb.insert(5, 0);
		rb.insert(2, 0);
		rb.insert(7, 7);
		rb.insert(14, 14);
		
		TreeMap<Integer, Integer> t = new TreeMap<Integer, Integer>();
		t.put(10, 0);
		t.put(15, 0);
		t.put(20, 0);
		t.put(12, 0);
		t.put(5, 0);
		t.put(2, 0);
		t.put(7, 7);
		t.put(14, 14);
		Set<Entry<Integer, Integer>> set = t.entrySet();
		for(Entry<Integer, Integer> e: set) {
			System.out.println(e.getKey());
		}
		

	}
}
