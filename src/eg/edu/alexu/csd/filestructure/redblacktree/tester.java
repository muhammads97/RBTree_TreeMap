package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.ArrayList;
import java.util.Random;

public class tester {
	public static void main(String[] args) {
		IRedBlackTree<Integer, Integer> rb = new RBTree<Integer,Integer>();
		Random r = new Random();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i = 0 ; i < 100 ;i++) {
			int k  =  r.nextInt(100);
			System.out.println(k);
			arr.add(k);
			rb.insert(k, k);
		}
		for(Integer i : arr) {
			
			boolean x = rb.delete(i);
			System.out.println(rb.contains(i));
			System.out.println(x);
			if(!x) {
				break;
			}
		}
		
	}

}
