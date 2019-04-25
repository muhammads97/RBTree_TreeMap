package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.HashSet;
import java.util.Random;

import org.junit.Assert;

public abstract class Test {

	public static void main(String[] args) {
		IRedBlackTree<Integer, Integer> rb = new RBTree<>();
		rb.insert(19, 0);
		rb.insert(25, 0);
		rb.insert(35, 0);
		rb.insert(45, 0);
		rb.insert(28, 0);
		rb.insert(36, 0);
		rb.insert(44, 0);
		rb.insert(22, 0);
		rb.insert(31, 0);
		rb.insert(40, 0);
		rb.insert(24, 0);
		System.out.println(rb.delete(19)+ "delete");
		System.out.println(rb.delete(35)+ "delete");
		System.out.println(rb.delete(28)+ "delete");
		System.out.println(rb.delete(45)+ "delete");
		System.out.println(rb.delete(25) +"delete");
		System.out.println(rb.delete(36)+ "delete");
		System.out.println(rb.delete(44)+ "delete");
		System.out.println(rb.delete(22)+ "delete");
		System.out.println(rb.delete(31)+ "delete");
		System.out.println(rb.delete(40) +"delete");
		System.out.println(rb.delete(24) +"delete");
		System.out.println(rb.getRoot().getValue());
	}
}
