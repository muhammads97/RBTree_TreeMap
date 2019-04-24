package eg.edu.alexu.csd.filestructure.redblacktree;

public class Node <T extends Comparable<T>, V> implements INode<T, V>{
	private INode<T, V> parent;
	private INode<T, V> l_child;
	private INode<T, V> r_child;
	private T key;
	private V value;
	private boolean color; 
	
	public Node() {
		parent = null;
		l_child = null;
		r_child = null;
		value = null;
		key = null;
		color = INode.BLACK;
	}
	
	@Override
	public void setParent(INode<T, V> parent) {
		this.parent = parent;
	}

	@Override
	public INode<T, V> getParent() {
		return parent;
	}

	@Override
	public void setLeftChild(INode<T, V> leftChild) {
		this.l_child = leftChild;
	}

	@Override
	public INode<T, V> getLeftChild() {
		return l_child;
	}

	@Override
	public void setRightChild(INode<T, V> rightChild) {
		this.r_child = rightChild;
	}

	@Override
	public INode<T, V> getRightChild() {
		return r_child;
	}

	@Override
	public T getKey() {
		return key;
	}

	@Override
	public void setKey(T key) {
		this.key = key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public boolean getColor() {
		return color;
	}

	@Override
	public void setColor(boolean color) {
		this.color = color;
	}

	@Override
	public boolean isNull() {
		return value == null;
	}

}
