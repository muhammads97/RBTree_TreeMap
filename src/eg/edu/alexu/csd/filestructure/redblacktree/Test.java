package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.HashSet;
import java.util.Random;

import org.junit.Assert;

public abstract class Test {

	public static void main(String[] args) {
		RBTree<Integer, Integer> rb = new RBTree<>();
		rb.insert(15, 0);
		rb.insert(10, 0);
		rb.insert(12, 0);
		rb.insert(13, 0);
		rb.insert(5, 0);
		rb.insert(2, 0);
		rb.insert(8, 0);
		rb.insert(17, 0);
		rb.insert(20, 0);
		rb.insert(25, 0);
		rb.insert(27, 0);
		rb.insert(22, 0);
		rb.insert(18, 0);
		TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>(rb);
		System.out.println(tm.floorKey(14));
	}
}
