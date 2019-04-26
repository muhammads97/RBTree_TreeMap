package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TreeMap<T extends Comparable<T>,V> implements ITreeMap<T, V> {
	private RBTree<T, V> rb;
	private INode<T, V> NIL ;
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
		INode<T, V> x =  rb.getRoot();
		Entry<T, V> e = null;
		while(true) {
			if(x == NIL) {
				return null;
			}
			if (key.compareTo(x.getKey()) < 0) {
						x = x.getLeftChild();
			} else if (key.compareTo(x.getKey()) > 0) {
						x = x.getRightChild();
			} else if (key.compareTo(x.getKey()) == 0) {
						e = new 
			}				
		}
		
		
	}
	
	@Override
	public T ceilingKey(T key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsKey(T key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(V value) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<T, V> floorEntry(T key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T floorKey(T key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(T key) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
