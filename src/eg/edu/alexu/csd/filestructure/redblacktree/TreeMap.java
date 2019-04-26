package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
