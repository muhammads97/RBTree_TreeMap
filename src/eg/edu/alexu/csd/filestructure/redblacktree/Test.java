package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.Map.Entry;

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
		



	}
}
