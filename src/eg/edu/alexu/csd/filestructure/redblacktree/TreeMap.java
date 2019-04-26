package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

import javax.management.RuntimeErrorException;


public class TreeMap<T extends Comparable<T>,V> implements ITreeMap<T, V> {
	private RBTree<T, V> rb;
	private INode<T, V> NIL ;
	TreeMap(RBTree<T, V> rb){
		this.rb = rb;
	}
	class mapEntry implements Entry<T, V>{
		private T key ;
		private V value;
		public mapEntry(T key ,V value) {
			this.key = key;
			this.value = value;
		}
		@Override
		public T getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		@Override
		public V setValue(V arg0) {
			V v =  value;
			value = arg0;
			return v;
		}
		
	}
	public TreeMap() {
		rb = new RBTree<>();
		NIL = rb.getNILNode();
		
	}
	
	@Override
	public Entry<T, V> ceilingEntry(T key) {
//		INode<T, V> x =  rb.getRoot();
//		Entry<T, V> e = null;
//		while(true) {
//			if(x == NIL) {
//				return null;
//			}
//			if (key.compareTo(x.getKey()) < 0) {
//						x = x.getLeftChild();
//			} else if (key.compareTo(x.getKey()) > 0) {
//						x = x.getRightChild();
//			} else if (key.compareTo(x.getKey()) == 0) {
//						e = new 
//			}				
//		}
//		
//		
		return null;
	}
	
	@Override
	public T ceilingKey(T key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		rb.clear();
		
	}

	@Override
	public boolean containsKey(T key) {
		
		return rb.contains(key);
	}

	@Override
	public boolean containsValue(V value) {
		INode<T, V> root = rb.getRoot();
		if(root == rb.getNILNode()) return false;
		Queue<INode<T, V>> q = new LinkedList<INode<T,V>>();
		q.add(root);
		while(!q.isEmpty()) {
			INode<T, V> n = q.poll();
			if(n.getValue().equals(value)) {
				return true;
			} else {
				if(n.getLeftChild() != rb.getNILNode()) {
					q.add(n.getLeftChild());
				}
				if(n.getRightChild() != rb.getNILNode()) {
					q.add(n.getRightChild());
				}
			}
		}
		return false;
	}

	@Override
	public Set<Entry<T, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<T, V> firstEntry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T firstKey() {
		INode<T, V> x = rb.getRoot();
		if(x == rb.getNILNode()) {
			return null;
		}
		else {
			while(x.getLeftChild() != rb.getNILNode()) {
				x = x.getLeftChild();
			}
		}
		return x.getKey();
	}

	@Override
	public Entry<T, V> floorEntry(T key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T floorKey(T key) {
		INode<T, V> y = rb.getNILNode();
		INode<T, V> x = rb.getRoot();
		while (x != rb.getNILNode()) {
			y = x;
			if (key.compareTo(x.getKey()) < 0) {
				x = x.getLeftChild();
			} else if (key.compareTo(x.getKey()) > 0) {
				x = x.getRightChild();
			} else if (key.compareTo(x.getKey()) == 0) {
				return key;
			}
		}
		if(key.compareTo(y.getKey()) > 0) {
			return y.getKey();
		}
		while(y != rb.getNILNode() && y != y.getParent().getRightChild()) {
			y = y.getParent();
		}
		if(y == rb.getNILNode()) return null;
		y = y.getParent();
		return y.getKey();
	}

	@Override
	public V get(T key) {
		return rb.search(key);
	}

	@Override
	public ArrayList<Entry<T, V>> headMap(T toKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Entry<T, V>> headMap(T toKey, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<T> keySet() {
		INode<T, V> root = rb.getRoot();
		
		Queue<INode<T, V>> q = new LinkedList<INode<T,V>>();
		Set<T> keySet = new HashSet<T>();
		if(root == rb.getNILNode()) return keySet;
		q.add(root);
		while(!q.isEmpty()) {
			INode<T, V> n = q.poll();
			keySet.add(n.getKey());
			if(n.getLeftChild() != rb.getNILNode()) {
				q.add(n.getLeftChild());
			}
			if(n.getRightChild() != rb.getNILNode()) {
				q.add(n.getRightChild());
			}
		}
		return keySet;
	}

	@Override
	public Entry<T, V> lastEntry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T lastKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<T, V> pollFirstEntry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<T, V> pollLastEntry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(T key, V value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void putAll(Map<T, V> map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean remove(T key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

}
