package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.HashSet;
import java.util.Random;

import org.junit.Assert;

public abstract class Test {

	public static void main(String[] args) {
			IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
					.getImplementationInstanceForInterface(IRedBlackTree.class);
			try {
				Random r = new Random();
				HashSet<Integer> list = new HashSet<Integer>();
				for (int i = 0; i < 100000; ++i) {
					int key = r.nextInt(100000);
					list.add(key);
					redBlackTree.insert(key, "soso" + key);
				}
				
				System.out.println(list.size());
				int i = 0;
				for (Integer elem : list) {
					
					Assert.assertTrue((boolean) redBlackTree.delete(elem));
				}
				
				INode node = redBlackTree.getRoot();
				if (node != null && !node.isNull()) {
					Assert.fail();
				}
			} catch (Throwable e) {
				TestRunner.fail("Fail to handle deletion", e);
			}	
	}
}
