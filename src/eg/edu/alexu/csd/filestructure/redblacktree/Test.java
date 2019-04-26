package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.HashSet;
import java.util.Random;

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
		t.put(10, 0);
		t.put(10, 0);
		t.put(10, 0);
		t.put(10, 0);
		t.put(10, 0);
		t.put(10, 0);
		
		System.out.println(t.ceilingEntry(13).getKey());
		
	}
}
