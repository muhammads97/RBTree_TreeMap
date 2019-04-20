package eg.edu.alexu.csd.filestructure.redblacktree;

public abstract class Test {

	public static void main(String[] args) {
		IRedBlackTree<Integer, Integer> t = new RBTree<Integer,Integer>();
		t.insert(0, 0);
		t.insert(1, 1);
		
		t.insert(2, 2);
		t.insert(3, 3);
	}

}
