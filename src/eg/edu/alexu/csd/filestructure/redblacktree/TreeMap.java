package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

import javax.management.RuntimeErrorException;

public class TreeMap<T extends Comparable<T>,V> implements ITreeMap<T, V> {
	private RBTree<T, V> rb;
	private INode<T, V> NIL ;
	private int size ;
	

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
		this.rb = new RBTree<>();
		size = 0;
		NIL = rb.getNILNode();
	}

	@Override
	public Entry<T, V> ceilingEntry(T key) {

		if(key == null) {
			throw new RuntimeErrorException(new Error());
		}
		INode<T, V> x =  rb.getRoot();
		Entry<T, V> e = null;
		INode<T, V> oldx = x;
		while(true) {
			if(x == NIL) {
				INode<T, V> y = oldx;
				while(y != NIL) {
					if(y.getKey().compareTo(key) > 0) {
						e = new mapEntry(y.getKey(),y.getValue());
						break;
					}
					x = y ;
					y = y.getParent();
				}
				break;
			}
			if (key.compareTo(x.getKey()) < 0) {
						oldx =x;
						x = x.getLeftChild();
			} else if (key.compareTo(x.getKey()) > 0) {
						oldx =x;
						x = x.getRightChild();
			} else if (key.compareTo(x.getKey()) == 0) {
						e = new mapEntry(key, x.getValue());
						break;
			}				
		}
			return e;
	}
	
	@Override
	public T ceilingKey(T key) {
		Entry<T, V> e = ceilingEntry(key);
		if(e == null) {
			return null;
		}
		return e.getKey();
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
		INode<T, V> root = rb.getRoot();
		Stack<INode<T, V>> s = new Stack<INode<T,V>>();
		Set<Entry<T, V>> entrySet = new LinkedHashSet<Entry<T, V>>();
		if(root == rb.getNILNode()) return entrySet;
		s.add(root);
		while(!s.isEmpty()) {
			INode<T, V> x = s.peek().getLeftChild();
			if(x != rb.getNILNode()) {
				s.push(x);
			} else {
				do {
				x = s.pop();
				entrySet.add(new mapEntry(x.getKey(), x.getValue()));
				//System.out.println(x.getKey());
				} while (!s.isEmpty() && x.getRightChild() == rb.getNILNode());
				if(x.getRightChild() != rb.getNILNode()) {
					s.push(x.getRightChild());
				}
			}
		}
		return entrySet;
	}

	@Override
	public Entry<T, V> firstEntry() {
		INode<T, V> x = rb.getRoot();
		if(x == rb.getNILNode()) {
			return null;
		}
		else {
			while(x.getLeftChild() != rb.getNILNode()) {
				x = x.getLeftChild();
			}
		}
		return new mapEntry(x.getKey(), x.getValue());
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
		INode<T, V> y = rb.getNILNode();
		INode<T, V> x = rb.getRoot();
		while (x != rb.getNILNode()) {
			y = x;
			if (key.compareTo(x.getKey()) < 0) {
				x = x.getLeftChild();
			} else if (key.compareTo(x.getKey()) > 0) {
				x = x.getRightChild();
			} else if (key.compareTo(x.getKey()) == 0) {
				return new mapEntry(x.getKey(), x.getValue());
			}
		}
		if(key.compareTo(y.getKey()) > 0) {
			return new mapEntry(y.getKey(), y.getValue());
		}
		while(y != rb.getNILNode() && y != y.getParent().getRightChild()) {
			y = y.getParent();
		}
		if(y == rb.getNILNode()) return null;
		y = y.getParent();
		return new mapEntry(y.getKey(), y.getValue());
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
		INode<T, V> x = rb.getRoot();
		if(x == rb.getNILNode()) {
			return null;
		}
		else {
			while(x.getRightChild() != rb.getNILNode()) {
				x = x.getRightChild();
			}
		}
		return new mapEntry(x.getKey(), x.getValue());
	}

	@Override
	public T lastKey() {
		INode<T, V> x = rb.getRoot();
		if(x == rb.getNILNode()) {
			return null;
		}
		else {
			while(x.getRightChild() != rb.getNILNode()) {
				x = x.getRightChild();
			}
		}
		return x.getKey();
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
		rb.insert(key, value);
		size++;
	}

	@Override
	public void putAll(Map<T, V> map) {
		if(map == null) {
			throw new RuntimeErrorException(new Error());
		}
		for(Entry<T, V> e :map.entrySet()) {
			rb.insert(e.getKey(), e.getValue());
		}
		size +=map.size();
	}

	@Override
	public boolean remove(T key) {
		boolean x = rb.delete(key);
		if(x) {
			size--;
		}
		return x;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

}
