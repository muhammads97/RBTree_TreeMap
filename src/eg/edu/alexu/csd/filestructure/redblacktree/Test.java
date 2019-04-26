package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.junit.Assert;

import eg.edu.alexu.csd.filestructure.redblacktree.TreeMap.mapEntry;

public abstract class Test {
	public static void main(String[] args) {
		TreeMap<Integer, Integer> t = new TreeMap<Integer, Integer>();
		t.put(10, 0);
		t.put(15, 0);

		t.put(5, 0);
		t.put(20, 0);
		t.put(13, 0);
		t.put(7, 0);
		t.put(2, 0);
		for(Entry<Integer, Integer> e : t.headMap(20)) {
			System.out.println(e.getKey());
		}
		

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
